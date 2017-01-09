package fr.renaudmathieu.swapi.domain.entities;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmModel implements Parcelable {

    public static final Creator<FilmModel> CREATOR = new Creator<FilmModel>() {
        @Override
        public FilmModel createFromParcel(Parcel in) {
            return new FilmModel(in);
        }

        @Override
        public FilmModel[] newArray(int size) {
            return new FilmModel[size];
        }
    };
    @SerializedName("title")
    private String mTitle;
    @SerializedName("episode_id")
    private String mId;
    @SerializedName("opening_crawl")
    private String mDescription;
    @SerializedName("director")
    private String mDirector;
    @SerializedName("producer")
    private String mProducer;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("url")
    private String mURL;
    @SerializedName("characters")
    private List<String> mPeopleList;

    protected FilmModel(Parcel in) {
        mTitle = in.readString();
        mId = in.readString();
        mDescription = in.readString();
        mDirector = in.readString();
        mProducer = in.readString();
        mReleaseDate = in.readString();
        mURL = in.readString();
        mPeopleList = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mId);
        dest.writeString(mDescription);
        dest.writeString(mDirector);
        dest.writeString(mProducer);
        dest.writeString(mReleaseDate);
        dest.writeString(mURL);
        dest.writeStringList(mPeopleList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDirector() {
        return mDirector;
    }

    public String getProducer() {
        return mProducer;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getURL() {
        return mURL;
    }

    public List<String> getPeopleList() {
        return mPeopleList;
    }
}

