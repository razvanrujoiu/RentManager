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
    private final LiveData<List<Route>> allResidences;

    public ResidenceViewModel(@NonNull Application application) {
        super(application);
        routeRepository = new RouteRepository(application);
        allResidences = routeRepository.getAllResidences();
    }

    public LiveData<List<Route>> getAllResidences() {
        return allResidences;
    }

    public LiveData<Route> getResidenceById(long residenceId) {
        return routeRepository.getResidenceById(residenceId);
    }

    public Long insertResidence(Route route) {
        return routeRepository.insert(route);
    }

    public void updateResidence(Route route) {
        routeRepository.updateResidence(route);
    }

    public void deleteResidence(Route route) {
        routeRepository.deleteResidence(route);
    }

    public void deleteAllResidences() {
        routeRepository.deleteAllResidences();
    }
}
