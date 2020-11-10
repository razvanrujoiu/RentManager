package com.example.rentmanager.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.rentmanager.models.Residence;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface ResidenceDao extends BaseDao<Residence> {

    @Query("SELECT * FROM Residence")
    LiveData<List<Residence>> getAllResidences();

    @Query("SELECT * FROM Residence WHERE userIdForeignKey = :userId")
    LiveData<Residence> getResidenceByUserId(Long userId);

    @Query("SELECT * FROM Residence WHERE residenceId = :residenceId")
    LiveData<Residence> getResidenceById(Long residenceId);

    @Query("DELETE FROM Residence")
    void deleteAllResidences();
}
