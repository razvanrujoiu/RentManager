package com.example.urbanmobility.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.urbanmobility.models.Route;

import java.util.List;

@Dao
public interface StationDao extends BaseDao<Route> {

    @Query("SELECT * FROM Route")
    LiveData<List<Route>> getAllResidences();

    @Query("SELECT * FROM Route WHERE userIdForeignKey = :userId")
    LiveData<Route> getResidenceByUserId(Long userId);

    @Query("SELECT * FROM Route WHERE routeId = :residenceId")
    LiveData<Route> getResidenceById(Long residenceId);

    @Query("DELETE FROM Route")
    void deleteAllResidences();
}
