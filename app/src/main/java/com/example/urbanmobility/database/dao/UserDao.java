package com.example.urbanmobility.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.urbanmobility.models.User;

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
