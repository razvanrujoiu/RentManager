package com.example.urbanmobility.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.urbanmobility.models.Route;
import com.example.urbanmobility.models.RouteWithStations;
import com.example.urbanmobility.models.Station;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RouteDao extends BaseDao<Route> {

    @Query("SELECT * FROM Route")
    LiveData<List<RouteWithStations>> getAllRoutes();

    @Query("SELECT * FROM Route WHERE userIdForeignKey = :userId")
    LiveData<RouteWithStations> getRouteByUserId(Long userId);

    @Query("SELECT * FROM Route WHERE routeId = :routeId")
    LiveData<RouteWithStations> getRouteById(Long routeId);

    @Query("DELETE FROM Route")
    void deleteAllRoutes();
}
