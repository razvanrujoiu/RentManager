package com.example.urbanmobility.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "userId",
        childColumns = "userIdForeignKey",
        onDelete = ForeignKey.CASCADE))
public class Route implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private Long routeId;

    @SerializedName("estimatedTime")
    @Expose
    private String estimatedTime;

    // Very important, must use ArrayList, not List, otherwise compiler will give error
    @SerializedName("stationList")
    @Expose
    @Embedded
    private ArrayList<Station> stationList;

    private Long userIdForeignKey;

    public Route() {
        this.estimatedTime = "";
        this.userIdForeignKey = 0L;
    }

    @Ignore
    public Route(String estimatedTime, Long userIdForeignKey) {
        this.estimatedTime = estimatedTime;
        this.userIdForeignKey = userIdForeignKey;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public ArrayList<Station> getStationList() {
        return stationList;
    }


    public void setStationList(ArrayList<Station> stationList) {
        this.stationList = stationList;
    }

    public Long getUserIdForeignKey() {
        return userIdForeignKey;
    }

    public void setUserIdForeignKey(Long userIdForeignKey) {
        this.userIdForeignKey = userIdForeignKey;
    }
}
