package fr.renaudmathieu.swapi.domain;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataListBean<T> {

    @SerializedName("count")
    private int mCount;

    @SerializedName("next")
    private String mNext;

    @SerializedName("previous")
    private String mPrevious;

    @SerializedName(value = "results")
    private List<T> mResults;

    public int getCount() {
        return mCount;
    }

    public String getNext() {
        return mNext;
    }

    public String getPrevious() {
        return mPrevious;
    }

    public List<T> getResults() {
        return mResults;
    }
}
