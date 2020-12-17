package com.example.urbanmobility.http;

import com.example.urbanmobility.models.NotificationList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotificationService {

    @GET(".")
    Call<NotificationList> getNotifications();
}
