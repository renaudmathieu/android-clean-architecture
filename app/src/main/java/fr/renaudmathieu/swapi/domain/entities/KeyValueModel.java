package fr.renaudmathieu.swapi.domain.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class KeyValueModel implements Parcelable {
    public static final Creator<KeyValueModel> CREATOR = new Creator<KeyValueModel>() {
        @Override
        public KeyValueModel createFromParcel(Parcel source) {
            return new KeyValueModel(source);
        }

        @Override
        public KeyValueModel[] newArray(int size) {
            return new KeyValueModel[size];
        }
    };
    private String mKey;
    private String mValue;
    private boolean mState;

    public KeyValueModel() {
    }

    public KeyValueModel(String key, String value) {
        mKey = key;
        mValue = value;
    }

    public KeyValueModel(String key, String value, boolean state) {
        this(key, value);
        mState = state;
    }

    protected KeyValueModel(Parcel in) {
        mKey = in.readString();
        mValue = in.readString();
        mState = in.readByte() != 0;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public boolean isState() {
        return mState;
    }

    public void setState(boolean state) {
        mState = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mKey);
        dest.writeString(mValue);
        dest.writeByte(mState ? (byte) 1 : (byte) 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyValueModel model = (KeyValueModel) o;

        return mKey.equals(model.mKey);

    }

    @Override
    public int hashCode() {
        return mKey.hashCode();
    }

    @Override
    public String toString() {
        return mValue;
    }
}
