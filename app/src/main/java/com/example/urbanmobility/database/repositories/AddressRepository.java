package com.example.urbanmobility.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.urbanmobility.database.DatabaseClient;
import com.example.urbanmobility.database.dao.AddressDao;
import com.example.urbanmobility.models.Address;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AddressRepository {

    private AddressDao addressDao;
    private LiveData<List<Address>> allAddresses;

    AddressRepository(Application application) {
        addressDao = DatabaseClient
                .getInstance(application.getApplicationContext())
                .getRentManagerDatabase()
                .addressDao();
        allAddresses = addressDao.getAllAddresses();
    }

    LiveData<List<Address>> getAllAddresses() {
        return allAddresses;
    }

    LiveData<Address> getAddressByResidenceId(long residenceId) {
        return addressDao.getAddressByResidenceId(residenceId);
    }

    Long insert(Address address) {
        AtomicLong addressId = new AtomicLong();
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            addressId.set(addressDao.insert(address));
        });
        return  addressId.get();
    }

    void deleteAddress(Address address) {
        DatabaseClient.databaseWriteExecutor.execute(() -> {
            addressDao.delete(address);
        });
    }

}
