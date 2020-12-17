package com.example.urbanmobility.http;

import com.example.urbanmobility.models.ResidenceList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResidenceService {

    @GET(".")
    Call<ResidenceList> getResidences();
}
