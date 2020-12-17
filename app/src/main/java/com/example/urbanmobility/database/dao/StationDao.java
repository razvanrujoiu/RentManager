package com.example.urbanmobility.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.urbanmobility.models.Station;

import java.util.List;

@Dao
public interface StationDao extends BaseDao<Station> {

    @Query("SELECT * FROM Station")
    LiveData<List<Station>> getAllStations();

    @Query("SELECT * FROM Station WHERE routeIdForeignKey = :id")
    LiveData<Station> getStationsByRouteId(Long id);

}
