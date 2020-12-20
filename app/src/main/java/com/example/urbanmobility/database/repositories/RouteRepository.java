package com.example.urbanmobility.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.urbanmobility.database.DatabaseClient;
import com.example.urbanmobility.database.dao.RouteDao;
import com.example.urbanmobility.models.Route;
import com.example.urbanmobility.models.RouteWithStations;
import com.example.urbanmobility.models.Station;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class RouteRepository {

    private RouteDao routeDao;
    private LiveData<List<RouteWithStations>> allRoutes;
    private StationRepository stationRepository;

    public RouteRepository(Application application) {
         routeDao = DatabaseClient
                 .getInstance(application.getApplicationContext())
                 .getUrbanMobilityDatabase()
                 .routeDao();
         stationRepository = new StationRepository(application);
         allRoutes = routeDao.getAllRoutes();
    }

    public LiveData<List<RouteWithStations>> getAllRoutes() {
        return allRoutes;
    }

    public LiveData<RouteWithStations> getRoutesByUserId(long userId) {
        return routeDao.getRouteByUserId(userId);
    }

    public LiveData<RouteWithStations> getRouteById(long routeId) {
        return routeDao.getRouteById(routeId);
    }



    public Long insert(RouteWithStations route) {
        AtomicLong routeId = new AtomicLong();
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            routeId.set(routeDao.insert(route.route));
            for(Station station : route.stations) {
                station.setRouteIdForeignKey(routeId.get());
                stationRepository.insert(station);
            }
        });
        return routeId.get();
    }

    public void updateRoute(RouteWithStations route) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            routeDao.update(route.route);
        });
    }

    public void deleteRoute(RouteWithStations route) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            routeDao.delete(route.route);
        });
    }

    public void deleteAllRoutes() {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            routeDao.deleteAllRoutes();
        });
    }
}
