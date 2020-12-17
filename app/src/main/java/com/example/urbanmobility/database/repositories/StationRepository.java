package com.example.urbanmobility.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.urbanmobility.database.DatabaseClient;
import com.example.urbanmobility.database.dao.StationDao;
import com.example.urbanmobility.models.Station;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class StationRepository {

    private StationDao stationDao;
    private LiveData<List<Station>> allStations;

    StationRepository(Application application) {
        stationDao = DatabaseClient
                .getInstance(application.getApplicationContext())
                .getUrbanMobilityDatabase()
                .stationDao();
        allStations = stationDao.getAllStations();
    }

    LiveData<List<Station>> getAllStations() {
        return allStations;
    }

//    LiveData<Station> getAddressByResidenceId(long residenceId) {
//        return stationDao.getStationsByRouteId(residenceId);
//    }

    Long insert(Station station) {
        AtomicLong addressId = new AtomicLong();
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            addressId.set(stationDao.insert(station));
        });
        return  addressId.get();
    }

    void deleteAddress(Station station) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            stationDao.delete(station);
        });
    }

}
