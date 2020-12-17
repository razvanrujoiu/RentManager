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

    @SerializedName("stationList")
    @Expose
    @Embedded
    private List<Station> stationList;

    private Long userIdForeignKey;

    public Route() {
        this.estimatedTime = "";
        this.stationList = new ArrayList<>();
        this.userIdForeignKey = 0L;
    }

    @Ignore
    public Route(String estimatedTime, List<Station> stationList, Long userIdForeignKey) {
        this.estimatedTime = estimatedTime;
        this.stationList = stationList;
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

    public List<Station> getStationList() {
        return stationList;
    }

    public void setStationList(List<Station> stationList) {
        this.stationList = stationList;
    }

    public Long getUserIdForeignKey() {
        return userIdForeignKey;
    }

    public void setUserIdForeignKey(Long userIdForeignKey) {
        this.userIdForeignKey = userIdForeignKey;
    }
}
