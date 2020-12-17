package com.example.urbanmobility.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.urbanmobility.database.repositories.RouteRepository;
import com.example.urbanmobility.models.Route;

import java.util.List;

public class ResidenceViewModel extends AndroidViewModel {

    private RouteRepository routeRepository;
    private final LiveData<List<Route>> allRoutes;

    public ResidenceViewModel(@NonNull Application application) {
        super(application);
        routeRepository = new RouteRepository(application);
        allRoutes = routeRepository.getAllRoutes();
    }

    public LiveData<List<Route>> getAllRoutes() {
        return allRoutes;
    }

    public LiveData<Route> getResidenceById(long residenceId) {
        return routeRepository.getResidenceById(residenceId);
    }

    public Long insertRoute(Route route) {
        return routeRepository.insert(route);
    }

    public void updateRoute(Route route) {
        routeRepository.updateRoute(route);
    }

    public void deleteRoute(Route route) {
        routeRepository.deleteRoute(route);
    }

    public void deleteAllRoutes() {
        routeRepository.deleteAllRoutes();
    }
}
