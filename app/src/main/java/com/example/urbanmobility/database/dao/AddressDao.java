package com.example.urbanmobility.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.urbanmobility.models.Address;

import java.util.List;

@Dao
public interface AddressDao extends BaseDao<Address> {

    @Query("SELECT * FROM Address")
    LiveData<List<Address>> getAllAddresses();

    @Query("SELECT * FROM Address WHERE residenceIdForeignKey = :id")
    LiveData<Address> getAddressByResidenceId(Long id);

}
