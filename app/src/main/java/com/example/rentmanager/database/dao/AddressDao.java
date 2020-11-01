package com.example.rentmanager.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.rentmanager.models.Address;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface AddressDao extends BaseDao<Address> {

    @Query("SELECT * FROM Address")
    LiveData<List<Address>> getAllAddresses();

    @Query("SELECT * FROM Address WHERE residenceIdForeignKey = :id")
    LiveData<Address> getAddressByResidenceId(Long id);

}
