package coderschool.com.java.newyorktimes.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import coderschool.com.java.newyorktimes.R;

import static coderschool.com.java.newyorktimes.R.id.ivThumbnail;

/**
 * Created by BuuPV on 2/25/2017.
 */


public class ArticleViewHolder extends RecyclerView.ViewHolder {
    TextView tvSnippet;
    TextView tvDate;
    TextView tvAuthor;
    TextView tvTime;
    TextView tvName;


    public ArticleViewHolder(View itemView) {
        super(itemView);
        tvSnippet = (TextView) itemView.findViewById(R.id.tvSnippet);
        tvName = (TextView) itemView.findViewById(R.id.tvName);

    }


    public TextView getTvSnippet() {
        return tvSnippet;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public void setTvSnippet(TextView tvSnippet) {
        this.tvSnippet = tvSnippet;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }

    public TextView getTvAuthor() {
        return tvAuthor;
    }

    public void setTvAuthor(TextView tvAuthor) {
        this.tvAuthor = tvAuthor;
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public void setTvTime(TextView tvTime) {
        this.tvTime = tvTime;
    }
}
