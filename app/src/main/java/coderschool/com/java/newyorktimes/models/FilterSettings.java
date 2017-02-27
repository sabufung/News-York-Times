package coderschool.com.java.newyorktimes.models;

import android.os.Parcelable;

import org.parceler.Parcel;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Created by BuuPV on 2/26/2017.
 */

@Parcel
public class FilterSettings{
//    Date beginDate;
    int sortType;
    List<String> newsDesk;
    boolean isArt;

    public FilterSettings() {
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public List<String> getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(List<String> newsDesk) {
        this.newsDesk = newsDesk;
    }

    public boolean isArt() {
        return isArt;
    }

    public void setArt(boolean art) {
        isArt = art;
    }
}
