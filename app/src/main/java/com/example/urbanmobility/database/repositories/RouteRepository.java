package com.example.urbanmobility.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.urbanmobility.database.DatabaseClient;
import com.example.urbanmobility.database.dao.RouteDao;
import com.example.urbanmobility.models.Route;
import com.example.urbanmobility.models.Station;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class RouteRepository {

    private RouteDao routeDao;
    private LiveData<List<Route>> allRoutes;
    private StationRepository stationRepository;

    public RouteRepository(Application application) {
         routeDao = DatabaseClient
                 .getInstance(application.getApplicationContext())
                 .getUrbanMobilityDatabase()
                 .routeDao();
         stationRepository = new StationRepository(application);
         allRoutes = routeDao.getAllRoutes();
    }

    public LiveData<List<Route>> getAllRoutes() {
        return allRoutes;
    }

    public LiveData<Route> getResidenceByUserId(long userId) {
        return routeDao.getRouteByUserId(userId);
    }

    public LiveData<Route> getResidenceById(long residenceId) {
        return routeDao.getRouteById(residenceId);
    }



    public Long insert(Route route) {
        AtomicLong residenceId = new AtomicLong();
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            residenceId.set(routeDao.insert(route));
            Station station = route.getStationList().get(0);
            station.setRouteIdForeignKey(residenceId.get());
            stationRepository.insert(station);
        });
        return residenceId.get();
    }

    public void updateRoute(Route route) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            routeDao.update(route);
        });
    }

    public void deleteRoute(Route route) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            routeDao.delete(route);
        });
    }

    public void deleteAllRoutes() {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            routeDao.deleteAllRoutes();
        });
    }
}
