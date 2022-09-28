package com.drillgil.mylecturersapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LecturerModel implements Parcelable {
    //Model fo our Lecturers
    @SerializedName("id")
    @Expose
    private final String lecturerId;
    @SerializedName("name")
    @Expose
    private final String lecturerName;
    @SerializedName("email")
    @Expose
    private final String lecturerEmail;
    @SerializedName("languages")
    @Expose
    private List<Integer> lecturerLanguages;

    public LecturerModel(String id, String name, String lecturerEmail, List<Integer> languages) {
        this.lecturerId = id;
        this.lecturerName = name;
        this.lecturerEmail = lecturerEmail;
        this.lecturerLanguages = languages;
    }

    protected LecturerModel(Parcel in) {
        lecturerId = in.readString();
        lecturerName = in.readString();
        lecturerEmail = in.readString();
    }

    public static final Creator<LecturerModel> CREATOR = new Creator<LecturerModel>() {
        @Override
        public LecturerModel createFromParcel(Parcel in) {
            return new LecturerModel(in);
        }

        @Override
        public LecturerModel[] newArray(int size) {
            return new LecturerModel[size];
        }
    };

    public String getLecturerId() {
        return lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public String getLecturerEmail() {
        return lecturerEmail;
    }

    public List<Integer> getLecturerLanguages() {
        return lecturerLanguages;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lecturerId);
        parcel.writeString(lecturerName);
        parcel.writeString(lecturerEmail);
    }
}
