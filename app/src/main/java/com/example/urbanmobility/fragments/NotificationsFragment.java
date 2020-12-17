package com.example.urbanmobility.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.urbanmobility.R;
import com.example.urbanmobility.Utils.InternetConnection;
import com.example.urbanmobility.adapters.NotificationsAdapter;
import com.example.urbanmobility.databinding.NotificationsFragmentBinding;
import com.example.urbanmobility.http.NotificationService;
import com.example.urbanmobility.http.RetrofitClient;
import com.example.urbanmobility.models.Notification;
import com.example.urbanmobility.models.NotificationList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private NotificationsFragmentBinding binding;
    private ArrayList<Notification> notifications = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotificationsAdapter adapter;
    private LinearLayoutManager layoutManager;

    public static NotificationsFragment newInstance() {
        return new NotificationsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = NotificationsFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        recyclerView = binding.notificationsRecyclerVIew;
        layoutManager = new LinearLayoutManager(getContext());
        setNotificationsViewAdapter();
        loadNotifications();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void setNotificationsViewAdapter() {
        adapter = new NotificationsAdapter(getContext(), notifications);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();

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
                        adapter.notifyDataSetChanged();
                        binding.progressBar.setVisibility(View.INVISIBLE);
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