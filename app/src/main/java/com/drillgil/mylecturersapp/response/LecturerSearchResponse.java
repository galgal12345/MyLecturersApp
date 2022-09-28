package com.drillgil.mylecturersapp.response;

import com.drillgil.mylecturersapp.models.LanguageModel;
import com.drillgil.mylecturersapp.models.LecturerModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//this class is for getting multiple lecturers (lecturer list)
public class LecturerSearchResponse {

    @SerializedName("Lecturers")
    @Expose
    private List<LecturerModel> lecturers;

    @SerializedName("Languages")
    @Expose
    private List<LanguageModel> languages;

    public List<LecturerModel> getLecturers(){
        return lecturers;
    }

    public List<LanguageModel> getLanguages(){
        return languages;
    }


}
