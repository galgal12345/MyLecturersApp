package com.drillgil.mylecturersapp.utils;

import com.drillgil.mylecturersapp.response.LecturerSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {
    //qkT9SPQU
    //search for lecturers
    @GET("raw/fk9QzFMT")
    Call<LecturerSearchResponse> searchLecturer();
}
