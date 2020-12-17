package com.example.urbanmobility.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.urbanmobility.database.DatabaseClient;
import com.example.urbanmobility.database.dao.RouteDao;
import com.example.urbanmobility.models.Station;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class StationRepository {

    private RouteDao routeDao;
    private LiveData<List<Station>> allAddresses;

    StationRepository(Application application) {
        routeDao = DatabaseClient
                .getInstance(application.getApplicationContext())
                .getRentManagerDatabase()
                .addressDao();
        allAddresses = routeDao.getAllAddresses();
    }

    LiveData<List<Station>> getAllAddresses() {
        return allAddresses;
    }

    LiveData<Station> getAddressByResidenceId(long residenceId) {
        return routeDao.getAddressByResidenceId(residenceId);
    }

    Long insert(Station station) {
        AtomicLong addressId = new AtomicLong();
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            addressId.set(routeDao.insert(station));
        });
        return  addressId.get();
    }

    void deleteAddress(Station station) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            routeDao.delete(station);
        });
    }

}
