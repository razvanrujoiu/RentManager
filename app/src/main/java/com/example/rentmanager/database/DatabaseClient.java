package com.example.rentmanager.database;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseClient {

    private Context context;

    private static DatabaseClient instance;

    private RentManagerDatabase rentManagerDatabase;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

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
