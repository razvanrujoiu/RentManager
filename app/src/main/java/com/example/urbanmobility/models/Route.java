package com.example.urbanmobility.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

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

    @PrimaryKey(autoGenerate = true)
    private Long routeId;

    @SerializedName("estimatedTime")
    @Expose
    private String estimatedTime;

    @SerializedName("routeNo")
    @Expose
    private String routeNo;

    private Long userIdForeignKey;

    public Route() {
        this.estimatedTime = "";
        this.userIdForeignKey = 0L;
        this.routeNo = "";
    }

    @Ignore
    public Route(String estimatedTime, String routeNo, Long userIdForeignKey) {
        this.estimatedTime = estimatedTime;
        this.userIdForeignKey = userIdForeignKey;
        this.routeNo = routeNo;
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

    public Long getUserIdForeignKey() {
        return userIdForeignKey;
    }

    public void setUserIdForeignKey(Long userIdForeignKey) {
        this.userIdForeignKey = userIdForeignKey;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(String routeNo) {
        this.routeNo = routeNo;
    }
}
