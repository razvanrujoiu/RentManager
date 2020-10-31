package com.example.rentmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.rentmanager.models.Residence;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface ResidenceDao extends BaseDao<Residence> {

    @Query("SELECT * FROM Residence")
    Observable<List<Residence>> getAllResidences();

    @Query("SELECT * FROM Residence WHERE userIdForeignKey = :userId")
    Observable<Residence> getResidenceByUserId(Long userId);
}
