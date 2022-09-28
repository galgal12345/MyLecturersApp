package com.drillgil.mylecturersapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguageModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private final int languageId;
    @SerializedName("name")
    @Expose
    private final String languageName;

    public LanguageModel(int languageId, String languageName) {
        this.languageId = languageId;
        this.languageName = languageName;
    }

    protected LanguageModel(Parcel in) {
        languageId = in.readInt();
        languageName = in.readString();
    }

    public static final Creator<LanguageModel> CREATOR = new Creator<LanguageModel>() {
        @Override
        public LanguageModel createFromParcel(Parcel in) {
            return new LanguageModel(in);
        }

        @Override
        public LanguageModel[] newArray(int size) {
            return new LanguageModel[size];
        }
    };

    public int getLanguageId() {
        return languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(languageId);
        parcel.writeString(languageName);
    }
}
