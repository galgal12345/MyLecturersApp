package com.drillgil.mylecturersapp.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.drillgil.mylecturersapp.AppExecutors;
import com.drillgil.mylecturersapp.models.LanguageModel;
import com.drillgil.mylecturersapp.models.LecturerModel;
import com.drillgil.mylecturersapp.response.LecturerSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;


public class MyApiClient {

    //LiveData
    private final MutableLiveData<List<LecturerModel>> mLecturers;
    private final MutableLiveData<List<LanguageModel>> mLanguages;

    private static MyApiClient instance;

    //making a Global Runnable
    private RetrieveLecturersRunnable retrieveLecturersRunnable;


    public static MyApiClient getInstance() {
        if (instance == null) {
            instance = new MyApiClient();
        }
        return instance;
    }

    private MyApiClient() {
        mLecturers = new MutableLiveData<>();
        mLanguages = new MutableLiveData<>();
    }

    public LiveData<List<LecturerModel>> getLecturers() {
        return mLecturers;
    }

    public LiveData<List<LanguageModel>> getLanguages(){
        return mLanguages;
    }

    public void SearchApi() {

        if (retrieveLecturersRunnable != null){
            retrieveLecturersRunnable = null;
        }
        retrieveLecturersRunnable = new RetrieveLecturersRunnable();
        final Future<?> myHandler = AppExecutors.getInstance().getNetworkIO().submit(retrieveLecturersRunnable);
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            //Cancelling the retrofit call
            myHandler.cancel(true);
        }, 5000, TimeUnit.SECONDS);

    }

    //Retrieving data from RESTApi by runnable class
    private class RetrieveLecturersRunnable implements Runnable {

        boolean cancelRequest;

        public RetrieveLecturersRunnable() {
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting the Response Object
            try{
                Response<LecturerSearchResponse> response = getLecturers().execute();
                if (cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    assert response.body() != null;
                    List<LecturerModel> lecturerModelList = new ArrayList<>(response.body().getLecturers());
                    List<LanguageModel> languageModelList = new ArrayList<>(response.body().getLanguages());

                    mLecturers.postValue(lecturerModelList);
                    mLanguages.postValue(languageModelList);
                }else{
                    assert response.errorBody() != null;
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error" + error);
                    mLecturers.postValue(null);
                    mLanguages.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mLecturers.postValue(null);
                mLanguages.postValue(null);

            }
        }

        //Search Method/ Query
        private Call<LecturerSearchResponse> getLecturers() {
            return Servicey.getMyApi().searchLecturer();
        }

        private void cancelRequest(){
            Log.v("Tag", "Canceling Search Request");
            cancelRequest = true;
        }
    }
}

