package com.example.urbanmobility.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NotificationList {

    @SerializedName("notifications")
    @Expose
    private ArrayList<Notification> notifications = null;

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }
}
