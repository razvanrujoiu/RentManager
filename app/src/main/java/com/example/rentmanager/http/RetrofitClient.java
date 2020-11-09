package com.example.rentmanager.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://api.mocki.io/v1/75be872d/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ResidenceService getResidenceService() {
        return getRetrofitInstance().create(ResidenceService.class);
    }


}
