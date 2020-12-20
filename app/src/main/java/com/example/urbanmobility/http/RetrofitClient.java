package com.example.urbanmobility.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String ROUTES_URL = "https://api.mocki.io/v1/c4474388/";

    public static final String NOTIFICATIONS_URL = "https://api.mocki.io/v1/cb06c5b6/";

    private static Retrofit getRetrofitInstance(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RouteService getRouteService() {
        return getRetrofitInstance(ROUTES_URL).create(RouteService.class);
    }

    public static NotificationService getNotificationService() {
        return getRetrofitInstance(NOTIFICATIONS_URL).create(NotificationService.class);
    }

}
