package com.example.urbanmobility.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.urbanmobility.database.dao.RouteDao;
import com.example.urbanmobility.database.dao.StationDao;
import com.example.urbanmobility.database.dao.UserDao;
import com.example.urbanmobility.models.Station;
import com.example.urbanmobility.models.Route;
import com.example.urbanmobility.models.User;

@Database(entities = {Station.class, Route.class, User.class}, version = 1, exportSchema = false)
public abstract class UrbanMobilityDatabase extends RoomDatabase {

    public abstract StationDao stationDao();

    public abstract RouteDao routeDao();

    public abstract UserDao userDao();
}