package com.example.urbanmobility.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.urbanmobility.database.DatabaseClient;
import com.example.urbanmobility.database.dao.ResidenceDao;
import com.example.urbanmobility.models.Address;
import com.example.urbanmobility.models.Residence;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ResidenceRepository {

    private ResidenceDao residenceDao;
    private LiveData<List<Residence>> allResidences;
    private AddressRepository addressRepository;

    public ResidenceRepository(Application application) {
         residenceDao = DatabaseClient
                 .getInstance(application.getApplicationContext())
                 .getRentManagerDatabase()
                 .residenceDao();
         addressRepository = new AddressRepository(application);
         allResidences = residenceDao.getAllResidences();
    }

    public LiveData<List<Residence>> getAllResidences() {
        return allResidences;
    }

    public LiveData<Residence> getResidenceByUserId(long userId) {
        return residenceDao.getResidenceByUserId(userId);
    }

    public LiveData<Residence> getResidenceById(long residenceId) {
        return residenceDao.getResidenceById(residenceId);
    }



    public Long insert(Residence residence) {
        AtomicLong residenceId = new AtomicLong();
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            residenceId.set(residenceDao.insert(residence));
            Address address = residence.getAddress();
            address.setResidenceIdForeignKey(residenceId.get());
            addressRepository.insert(address);
        });
        return residenceId.get();
    }

    public void updateResidence(Residence residence) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            residenceDao.update(residence);
        });
    }

    public void deleteResidence(Residence residence) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            residenceDao.delete(residence);
        });
    }

    public void deleteAllResidences() {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            residenceDao.deleteAllResidences();
        });
    }
}
