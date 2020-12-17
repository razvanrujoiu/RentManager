package com.example.urbanmobility.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.urbanmobility.R;
import com.example.urbanmobility.Utils.InternetConnection;
import com.example.urbanmobility.http.NotificationService;
import com.example.urbanmobility.http.RetrofitClient;
import com.example.urbanmobility.models.Notification;
import com.example.urbanmobility.models.NotificationList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private ArrayList<Notification> notifications = new ArrayList<>();

    public static NotificationsFragment newInstance() {
        return new NotificationsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        loadNotifications();
        return inflater.inflate(R.layout.notifications_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    void loadNotifications() {
        if (InternetConnection.checkConnection(getContext())) {
            NotificationService notificationService = RetrofitClient.getNotificationService();
            Call<NotificationList> call = notificationService.getNotifications();

            call.enqueue(new Callback<NotificationList>() {
                @Override
                public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
                    if (response.isSuccessful()) {
                        notifications.addAll(response.body().getNotifications());
                    }
                }

                @Override
                public void onFailure(Call<NotificationList> call, Throwable t) {
                    Toast.makeText(getContext(), "Error while fetching data from API", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}