package fr.renaudmathieu.swapi.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RootModel implements Parcelable {

    @SerializedName("films")
    private String mFilms;

    @SerializedName("people")
    private String mPeople;

    @SerializedName("planets")
    private String mPlanets;

    @SerializedName("species")
    private String mSpecies;

    @SerializedName("starships")
    private String mStarships;

    @SerializedName("vehicles")
    private String mVehicles;


    protected RootModel(Parcel in) {
        mFilms = in.readString();
        mPeople = in.readString();
        mPlanets = in.readString();
        mSpecies = in.readString();
        mStarships = in.readString();
        mVehicles = in.readString();
    }

    public static final Creator<RootModel> CREATOR = new Creator<RootModel>() {
        @Override
        public RootModel createFromParcel(Parcel in) {
            return new RootModel(in);
        }

        @Override
        public RootModel[] newArray(int size) {
            return new RootModel[size];
        }
    };

    public String getFilms() {
        return mFilms;
    }

    public String getPeople() {
        return mPeople;
    }

    public String getPlanets() {
        return mPlanets;
    }

    public String getSpecies() {
        return mSpecies;
    }

    public String getStarships() {
        return mStarships;
    }

    public String getVehicles() {
        return mVehicles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFilms);
        dest.writeString(mPeople);
        dest.writeString(mPlanets);
        dest.writeString(mSpecies);
        dest.writeString(mStarships);
        dest.writeString(mVehicles);
    }
}
