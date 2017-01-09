package fr.renaudmathieu.swapi.domain.entities;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PeopleModel implements Parcelable {

    public static final Creator<PeopleModel> CREATOR = new Creator<PeopleModel>() {
        @Override
        public PeopleModel createFromParcel(Parcel in) {
            return new PeopleModel(in);
        }

        @Override
        public PeopleModel[] newArray(int size) {
            return new PeopleModel[size];
        }
    };
    @SerializedName("name")
    private String mName;
    @SerializedName("height")
    private int mHeight;
    @SerializedName("mass")
    private int mMass;
    @SerializedName("hair_color")
    private String mHairColor;
    @SerializedName("eye_color")
    private String mEyeColor;
    @SerializedName("birth_year")
    private String mBirthday;
    @SerializedName("gender")
    private String mGender;

    protected PeopleModel(Parcel in) {
        mName = in.readString();
        mHeight = in.readInt();
        mMass = in.readInt();
        mHairColor = in.readString();
        mEyeColor = in.readString();
        mBirthday = in.readString();
        mGender = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mHeight);
        dest.writeInt(mMass);
        dest.writeString(mHairColor);
        dest.writeString(mEyeColor);
        dest.writeString(mBirthday);
        dest.writeString(mGender);
    }

    public String getName() {
        return mName;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getMass() {
        return mMass;
    }

    public String getHairColor() {
        return mHairColor;
    }

    public String getEyeColor() {
        return mEyeColor;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public String getGender() {
        return mGender;
    }
}
