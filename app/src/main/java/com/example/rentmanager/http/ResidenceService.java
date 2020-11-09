package com.example.rentmanager.http;

import com.example.rentmanager.models.ResidenceList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResidenceService {

    @GET
    Call<ResidenceList> getResidences();
}
