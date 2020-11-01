package com.example.rentmanager.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.rentmanager.models.User;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface UserDao extends BaseDao<User> {

    @Query("SELECT * FROM User WHERE userId = :userId")
    LiveData<User> getUserById(Long userId);

}
