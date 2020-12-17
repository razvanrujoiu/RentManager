package com.example.urbanmobility.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.urbanmobility.models.Route;

import java.util.List;

@Dao
public interface RouteDao extends BaseDao<Route> {

    @Query("SELECT * FROM Route")
    LiveData<List<Route>> getAllRoutes();

    @Query("SELECT * FROM Route WHERE userIdForeignKey = :userId")
    LiveData<Route> getRouteByUserId(Long userId);

    @Query("SELECT * FROM Route WHERE routeId = :routeId")
    LiveData<Route> getRouteById(Long routeId);

    @Query("DELETE FROM Route")
    void deleteAllRoutes();
}
