package com.drillgil.mylecturersapp.repositories;

import androidx.lifecycle.LiveData;

import com.drillgil.mylecturersapp.models.LanguageModel;
import com.drillgil.mylecturersapp.models.LecturerModel;
import com.drillgil.mylecturersapp.request.MyApiClient;

import java.util.List;

public class MyRepository {
    //this class is acting as repositories

    private static MyRepository instance;

    //LiveData
    private final MyApiClient myApiClient;

    public static MyRepository getInstance() {
        if (instance == null) {
            instance = new MyRepository();
        }
        return instance;
    }

    private MyRepository() {
        myApiClient = MyApiClient.getInstance();
    }

    public LiveData<List<LecturerModel>> getLecturers(){return myApiClient.getLecturers();}

    public LiveData<List<LanguageModel>> getLanguages(){
        return myApiClient.getLanguages();
    }

    //2 - Calling the method in Repository
    public void SearchApi(){
        myApiClient.SearchApi();
    }
}
