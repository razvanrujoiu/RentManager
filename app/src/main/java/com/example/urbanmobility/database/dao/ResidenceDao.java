package com.example.urbanmobility.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.urbanmobility.models.Residence;

import java.util.List;

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
