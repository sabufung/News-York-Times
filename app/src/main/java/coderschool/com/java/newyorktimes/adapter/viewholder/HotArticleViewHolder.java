package coderschool.com.java.newyorktimes.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import coderschool.com.java.newyorktimes.R;

import static coderschool.com.java.newyorktimes.R.id.tvSnippet;

/**
 * Created by BuuPV on 2/25/2017.
 */

public class HotArticleViewHolder extends RecyclerView.ViewHolder{
    TextView tvName;
    ImageView ivThumbnail;

    public HotArticleViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        ivThumbnail = (ImageView) itemView.findViewById(R.id.ivThumbnail);

    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public ImageView getIvThumbnail() {
        return ivThumbnail;
    }

    public void setIvThumbnail(ImageView ivThumbnail) {
        this.ivThumbnail = ivThumbnail;
    }
}
