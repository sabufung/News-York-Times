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
    boolean isArt;
    boolean isFashion;
    boolean isSport;



    public FilterSettings() {
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public boolean isFashion() {
        return isFashion;
    }

    public void setFashion(boolean fashion) {
        isFashion = fashion;
    }

    public boolean isSport() {
        return isSport;
    }

    public void setSport(boolean sport) {
        isSport = sport;
    }

    public boolean isArt() {
        return isArt;
    }

    public void setArt(boolean art) {
        isArt = art;
    }
}
