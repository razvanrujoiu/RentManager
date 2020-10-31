package com.example.rentmanager.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context context;

    private static DatabaseClient instance;

    private RentManagerDatabase rentManagerDatabase;

    private DatabaseClient(Context context) {
        this.context = context;
        rentManagerDatabase = Room.databaseBuilder(context, RentManagerDatabase.class, "rentmanagerdb").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public RentManagerDatabase getRentManagerDatabase() {
        return rentManagerDatabase;
    }
}
