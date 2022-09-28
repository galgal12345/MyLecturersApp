package com.drillgil.mylecturersapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.drillgil.mylecturersapp.models.LanguageModel;
import com.drillgil.mylecturersapp.models.LecturerModel;
import com.drillgil.mylecturersapp.repositories.MyRepository;

import java.util.List;

public class MyListViewModel extends ViewModel {

    private final MyRepository myRepository;

    //Constructor
    public MyListViewModel(){
        myRepository = MyRepository.getInstance();
    }

    public LiveData<List<LecturerModel>> getLecturers(){
        return myRepository.getLecturers();
    }

    public LiveData<List<LanguageModel>> getLanguages(){
        return myRepository.getLanguages();
    }

    //1 - Calling method in view-model
    public void SearchApi(){
        myRepository.SearchApi();
    }
}
