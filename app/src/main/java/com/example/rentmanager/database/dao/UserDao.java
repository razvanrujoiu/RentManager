package com.example.rentmanager.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rentmanager.models.User;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface UserDao extends BaseDao<User> {

    @Query("SELECT * FROM User WHERE userId = :userId")
    User getUserById(Long userId);

    @Query("SELECT * FROM User WHERE emailAddress = :mail")
    User getUserByMail(String mail);

    @Insert
    Long insertUser(User user);

    @Update
    void updateUser(User user);
}
