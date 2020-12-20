package com.example.urbanmobility.http;

import com.example.urbanmobility.models.RouteList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RouteService {

    @GET(".")
    Call<RouteList> getResidences();
}
