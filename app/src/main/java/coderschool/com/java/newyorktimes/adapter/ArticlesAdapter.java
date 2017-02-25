package coderschool.com.java.newyorktimes.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import coderschool.com.java.newyorktimes.R;
import coderschool.com.java.newyorktimes.activity.ArticleDetailActivity;
import coderschool.com.java.newyorktimes.adapter.viewholder.ArticleViewHolder;
import coderschool.com.java.newyorktimes.adapter.viewholder.HotArticleViewHolder;
import coderschool.com.java.newyorktimes.common.Constant;
import coderschool.com.java.newyorktimes.models.Doc;

import static android.R.attr.start;

/**
 * Created by BuuPV on 2/24/2017.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Doc dataSet;
    List<Doc> docs = new ArrayList<>();
    Context context;
    Configuration configuration;
    private static final int HOT = 1, NORMAL = 0;


    public ArticlesAdapter(List<Doc> docs) {
        this.docs = docs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        context = parent.getContext();
        configuration = context.getResources().getConfiguration();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            default:
            case NORMAL:
                view = inflater.inflate(R.layout.article, parent, false);
                viewHolder = new ArticleViewHolder(view);
                break;
            case HOT:
                view = inflater.inflate(R.layout.hot_article, parent, false);
                viewHolder = new HotArticleViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            default:
            case NORMAL:
                ArticleViewHolder vh1 = (ArticleViewHolder) holder;
                configureArticleViewHolder(vh1, position);
                break;
            case HOT:
                HotArticleViewHolder vh2 = (HotArticleViewHolder) holder;
                configureHotArticleViewHolder2(vh2, position);
                break;
        }
    }

    private void configureHotArticleViewHolder2(HotArticleViewHolder vh2, int position) {

    }

    private void configureArticleViewHolder(ArticleViewHolder vh1, int position) {
        final Doc doc = docs.get(position);
        vh1.getTvDate().setText(doc.getPubDate());
        vh1.getTvSnippet().setText(doc.getSnippet());
        vh1.getTvName().setText(doc.getHeadline().getMain());
        vh1.getTvName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ArticleDetailActivity.class);
                i.putExtra(Constant.ARTICLE_URL, doc.getWebUrl());
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return docs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return NORMAL;
    }

    public void addAll(List<Doc> docList) {
        docs.addAll(docList);
        this.notifyDataSetChanged();
    }

    public void clear() {
        docs.clear();
        this.notifyDataSetChanged();
    }
}


