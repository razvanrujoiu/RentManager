package com.example.urbanmobility.database;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseClient {

    private Context context;

    private static DatabaseClient instance;

    private UrbanMobilityDatabase urbanMobilityDatabase;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private DatabaseClient(Context context) {
        this.context = context;
        urbanMobilityDatabase = Room.databaseBuilder(context, UrbanMobilityDatabase.class, "rentmanagerdb").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public UrbanMobilityDatabase getUrbanMobilityDatabase() {
        return urbanMobilityDatabase;
    }
}
