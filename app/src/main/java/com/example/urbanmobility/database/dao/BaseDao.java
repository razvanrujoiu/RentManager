package com.example.urbanmobility.database.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(T object);

    @Update
    void update(T object);

    @Delete
    void delete(T object);


}
