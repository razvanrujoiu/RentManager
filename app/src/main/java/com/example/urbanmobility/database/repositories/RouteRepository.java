package com.example.urbanmobility.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.urbanmobility.database.DatabaseClient;
import com.example.urbanmobility.database.dao.StationDao;
import com.example.urbanmobility.models.Route;
import com.example.urbanmobility.models.Station;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class RouteRepository {

    private StationDao stationDao;
    private LiveData<List<Route>> allResidences;
    private StationRepository stationRepository;

    public RouteRepository(Application application) {
         stationDao = DatabaseClient
                 .getInstance(application.getApplicationContext())
                 .getRentManagerDatabase()
                 .residenceDao();
         stationRepository = new StationRepository(application);
         allResidences = stationDao.getAllResidences();
    }

    public LiveData<List<Route>> getAllResidences() {
        return allResidences;
    }

    public LiveData<Route> getResidenceByUserId(long userId) {
        return stationDao.getResidenceByUserId(userId);
    }

    public LiveData<Route> getResidenceById(long residenceId) {
        return stationDao.getResidenceById(residenceId);
    }



    public Long insert(Route route) {
        AtomicLong residenceId = new AtomicLong();
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            residenceId.set(stationDao.insert(route));
            Station station = route.getStationList().get(0);
            station.setRouteIdForeignKey(residenceId.get());
            stationRepository.insert(station);
        });
        return residenceId.get();
    }

    public void updateResidence(Route route) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            stationDao.update(route);
        });
    }

    public void deleteResidence(Route route) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            stationDao.delete(route);
        });
    }

    public void deleteAllResidences() {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            stationDao.deleteAllResidences();
        });
    }
}
