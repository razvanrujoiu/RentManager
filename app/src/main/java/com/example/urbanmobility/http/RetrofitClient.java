package com.example.urbanmobility.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://api.mocki.io/v1/75be872d/";

    public static final String NOTIFICATIONS_URL = "https://api.mocki.io/v1/cb06c5b6/";

    private static Retrofit getRetrofitInstance(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RouteService getResidenceService() {
        return getRetrofitInstance(BASE_URL).create(RouteService.class);
    }

    public static NotificationService getNotificationService() {
        return getRetrofitInstance(NOTIFICATIONS_URL).create(NotificationService.class);
    }

}
