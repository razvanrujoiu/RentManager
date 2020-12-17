package com.example.urbanmobility.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbanmobility.databinding.NotificationItemBinding;
import com.example.urbanmobility.models.Notification;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

    private Context context;
    private ArrayList<Notification> notifications;


    public NotificationsAdapter(Context context, ArrayList<Notification> notifications) {
        this.context  = context;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        NotificationItemBinding binding = NotificationItemBinding.inflate(layoutInflater, parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notifications.get(position);
        holder.bind(notification);
    }

    @Override
    public int getItemCount() {
        return notifications != null ? notifications.size() : 0;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        private NotificationItemBinding binding;

        public NotificationViewHolder(NotificationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Notification notification) {
            binding.textViewTitle.setText(notification.getTitle());
            binding.textViewDescription.setText(notification.getDescription());
            binding.textViewDate.setText(notification.getDate());
        }
    }
}
