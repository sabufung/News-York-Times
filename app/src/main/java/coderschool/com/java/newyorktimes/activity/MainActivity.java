package coderschool.com.java.newyorktimes.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import coderschool.com.java.newyorktimes.R;
import coderschool.com.java.newyorktimes.adapter.ArticlesAdapter;
import coderschool.com.java.newyorktimes.network.ArticleAPIEndpoint;
import coderschool.com.java.newyorktimes.models.Doc;
import coderschool.com.java.newyorktimes.models.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.flexbox.FlexboxItemDecoration.BOTH;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rvArticle)
    RecyclerView rvArticle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    ArticlesAdapter mAdapter;
    FlexboxLayoutManager mFlexboxLayoutManager;
    List<Doc> docList = new ArrayList<>();
    int currentPage=1;
    String searchTerm;
    boolean isLoadMore;

    ArticleAPIEndpoint articleAPIEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        articleAPIEndpoint = retrofit.create(ArticleAPIEndpoint.class);

        mFlexboxLayoutManager = new FlexboxLayoutManager();
        mFlexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        mFlexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        mFlexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
        rvArticle.setHasFixedSize(true);
        rvArticle.setLayoutManager(mFlexboxLayoutManager);
        mAdapter = new ArticlesAdapter(docList);
        rvArticle.setAdapter(mAdapter);

        rvArticle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoadMore)
                    return;
                if (dy < 0){
                    fetchArticle(searchTerm,null,null,++currentPage,false);
                    isLoadMore = true;
                }
            }
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                fetchArticle(searchTerm,null,null,0,true);
            }
        });



        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        fetchArticle(null, null, null, 0,true);
    }

    private void fetchArticle(String q, String begin_date, String end_date, int page, final boolean reset) {
        searchTerm = q;
        Log.d("Buu",String.valueOf(page));
        articleAPIEndpoint.getArticle(q, begin_date, end_date, page).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                swipeContainer.setRefreshing(false);
                isLoadMore = false;
                if (reset){
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
                        if (query.isEmpty()){
                            fetchArticle(null,null,null,0,true);
                            return true;
                        }
                        fetchArticle(query,null,null,0,true);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (newText.isEmpty()){
                            fetchArticle(null,null,null,0,true);
                        }
                        return false;
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }

}
