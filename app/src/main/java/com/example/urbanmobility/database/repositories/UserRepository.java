package com.example.urbanmobility.database.repositories;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.urbanmobility.database.DatabaseClient;
import com.example.urbanmobility.database.dao.UserDao;
import com.example.urbanmobility.models.User;

import java.util.concurrent.atomic.AtomicLong;

public class UserRepository {

    private UserDao userDao;
    private User currentUser;

    UserRepository(Application application) {
        userDao = DatabaseClient
                .getInstance(application.getApplicationContext())
                .getRentManagerDatabase()
                .userDao();
        SharedPreferences sharedPreferences = application.getSharedPreferences("user", Context.MODE_PRIVATE);
        long userId = sharedPreferences.getLong("userId",0);
        currentUser = userDao.getUserById(userId);
    }

    Long insert(User user) {
        AtomicLong userId = new AtomicLong();
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            userId.set(userDao.insert(user));
        });
        return userId.get();
    }


}
