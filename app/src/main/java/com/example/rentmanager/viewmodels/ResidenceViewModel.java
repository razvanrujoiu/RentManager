package com.example.rentmanager.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rentmanager.database.repositories.ResidenceRepository;
import com.example.rentmanager.models.Residence;

import java.util.List;

public class ResidenceViewModel extends AndroidViewModel {

    private ResidenceRepository residenceRepository;
    private final LiveData<List<Residence>> allResidences;

    public ResidenceViewModel(@NonNull Application application) {
        super(application);
        residenceRepository = new ResidenceRepository(application);
        allResidences = residenceRepository.getAllResidences();
    }

    public LiveData<List<Residence>> getAllResidences() {
        return allResidences;
    }

    public LiveData<Residence> getResidenceById(long residenceId) {
        return residenceRepository.getResidenceById(residenceId);
    }

    public Long insertResidence(Residence residence) {
        return residenceRepository.insert(residence);
    }

    public void updateResidence(Residence residence) {
        residenceRepository.updateResidence(residence);
    }

    public void deleteResidence(Residence residence) {
        residenceRepository.deleteResidence(residence);
    }

    public void deleteAllResidences() {
        residenceRepository.deleteAllResidences();
    }
}
