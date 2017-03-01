package coderschool.com.java.newyorktimes.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coderschool.com.java.newyorktimes.EndlessRecyclerViewScrollListener;
import coderschool.com.java.newyorktimes.R;
import coderschool.com.java.newyorktimes.VerticalSpaceItemDecoration;
import coderschool.com.java.newyorktimes.activity.fragment.FilterFragment;
import coderschool.com.java.newyorktimes.adapter.ArticlesAdapter;
import coderschool.com.java.newyorktimes.models.FilterSettings;
import coderschool.com.java.newyorktimes.network.ArticleAPIEndpoint;
import coderschool.com.java.newyorktimes.models.Doc;
import coderschool.com.java.newyorktimes.models.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.flexbox.FlexboxItemDecoration.BOTH;

public class MainActivity extends BaseActivity implements FilterFragment.OnFragmentInteractionListener {
    @BindView(R.id.rvArticle)
    RecyclerView rvArticle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;


    ArticlesAdapter mAdapter;
    //    FlexboxLayoutManager mFlexboxLayoutManager;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    List<Doc> docList = new ArrayList<>();
    int currentPage = 1;
    String searchTerm;
    boolean isLoadMore;
    FilterSettings filter;
    EndlessRecyclerViewScrollListener mScrollListener;


    ArticleAPIEndpoint articleAPIEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        articleAPIEndpoint = retrofit.create(ArticleAPIEndpoint.class);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        VerticalSpaceItemDecoration dividerItemDecoration = new VerticalSpaceItemDecoration(10);
        rvArticle.addItemDecoration(dividerItemDecoration);
        rvArticle.setLayoutManager(staggeredGridLayoutManager);

//        mFlexboxLayoutManager = new FlexboxLayoutManager();
//        mFlexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
//        mFlexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
//        mFlexboxLayoutManager.setAlignItems(AlignItems.FLEX_START);
//        rvArticle.setHasFixedSize(true);
//        rvArticle.setLayoutManager(mFlexboxLayoutManager);
        mAdapter = new ArticlesAdapter(docList);
        rvArticle.setAdapter(mAdapter);
        mScrollListener = new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                fetchArticle(searchTerm, null, null, ++currentPage, false);
            }
        };
        rvArticle.addOnScrollListener(mScrollListener);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                fetchArticle(searchTerm, null, null, 0, true);
            }
        });


        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        if (isNetworkAvailable()) {
            if (isOnline()) {
                fetchArticle(null, null, null, 0, true);
            } else {
                Toast.makeText(this, "Please check your network connectivity", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please Enable Network", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchArticle(String q, String begin_date, String end_date, int page, final boolean reset) {
        showProgressDialog();
        searchTerm = q;

        articleAPIEndpoint.getArticle(q, begin_date, end_date, page).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                dismissProgressDialog();
                swipeContainer.setRefreshing(false);
                isLoadMore = false;
                if (reset) {
                    mAdapter.clear();
                }
                mAdapter.addAll(response.body().getResponse().getDocs());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                swipeContainer.setRefreshing(false);
                isLoadMore = false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                final SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        searchView.clearFocus();
                        if (query.isEmpty()) {
                            fetchArticle(null, null, null, 0, true);
                            return true;
                        }
                        fetchArticle(query, null, null, 0, true);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        return false;
                    }
                });
                break;
            case R.id.action_filter:
                showFilter();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFilter() {
        if (filter == null) {
            filter = new FilterSettings();
        }
        FragmentManager fm = getSupportFragmentManager();
        FilterFragment filterFragment = FilterFragment.newInstance(filter);
        filterFragment.show(fm, "fragment_filter");
    }


    @Override
    public void onFragmentInteraction(FilterSettings filter) {
        this.filter = filter;
    }
}
