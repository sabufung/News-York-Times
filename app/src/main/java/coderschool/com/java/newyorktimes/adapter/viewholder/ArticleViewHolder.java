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
    ImageView ivThumbnail;


    public ArticleViewHolder(View itemView) {
        super(itemView);
        tvSnippet = (TextView) itemView.findViewById(R.id.tvSnippet);
//        tvDate = (TextView) itemView.findViewById(R.id.tvDate);
//        tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
//        tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        ivThumbnail = (ImageView) itemView.findViewById(R.id.ivThumbnail);

    }

    public ImageView getIvThumbnail() {
        return ivThumbnail;
    }

    public void setIvThumbnail(ImageView ivThumbnail) {
        this.ivThumbnail = ivThumbnail;
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
