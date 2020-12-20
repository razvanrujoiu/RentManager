package com.example.urbanmobility.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.urbanmobility.database.repositories.RouteRepository;
import com.example.urbanmobility.models.Route;
import com.example.urbanmobility.models.RouteWithStations;

import java.util.List;

public class RouteViewModel extends AndroidViewModel {

    private RouteRepository routeRepository;
    private final LiveData<List<RouteWithStations>> allRoutes;

    public RouteViewModel(@NonNull Application application) {
        super(application);
        routeRepository = new RouteRepository(application);
        allRoutes = routeRepository.getAllRoutes();
    }

    public LiveData<List<RouteWithStations>> getAllRoutes() {
        return allRoutes;
    }

    public LiveData<RouteWithStations> getResidenceById(long residenceId) {
        return routeRepository.getResidenceById(residenceId);
    }

    public Long insertRoute(RouteWithStations route) {
        return routeRepository.insert(route);
    }

    public void updateRoute(RouteWithStations route) {
        routeRepository.updateRoute(route);
    }

    public void deleteRoute(RouteWithStations route) {
        routeRepository.deleteRoute(route);
    }

    public void deleteAllRoutes() {
        routeRepository.deleteAllRoutes();
    }
}
