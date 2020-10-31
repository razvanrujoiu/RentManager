package com.example.rentmanager.database.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T object);

    @Update
    void update(T object);

    @Delete
    void delete(T object);


}
