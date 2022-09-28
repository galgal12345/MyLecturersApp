package com.drillgil.mylecturersapp.request;

import com.drillgil.mylecturersapp.utils.Credentials;
import com.drillgil.mylecturersapp.utils.MyApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {

        private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(Credentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        private static final Retrofit retrofit = retrofitBuilder.build();
        private static final MyApi myApi = retrofit.create(MyApi.class);

        public static MyApi getMyApi(){
                return myApi;
        }
}
