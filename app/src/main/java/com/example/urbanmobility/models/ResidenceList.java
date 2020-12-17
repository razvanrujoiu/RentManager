package com.example.urbanmobility.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResidenceList {

    @SerializedName("residences")
    @Expose
    private ArrayList<Residence> residences = null;

    public ArrayList<Residence> getResidences() {
        return residences;
    }

    public void setResidences(ArrayList<Residence> residences) {
        this.residences = residences;
    }
}
