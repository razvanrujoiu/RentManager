package com.example.urbanmobility.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(foreignKeys = @ForeignKey(entity = Route.class,
        parentColumns = "routeId",
        childColumns = "routeIdForeignKey",
        onDelete = ForeignKey.CASCADE))
public class Station {

    @PrimaryKey(autoGenerate = true)
    private Long stationId;

    @SerializedName("stationName")
    @Expose
    private String stationName;

    @SerializedName("streetName")
    @Expose
    private String streetName;

    @SerializedName("arrivalHour")
    @Expose
    private String arrivalHour;

    @SerializedName("departureHour")
    @Expose
    private String departureHour;

    private Long routeIdForeignKey;

    public Station() {
        this.streetName = "";
        this.stationName = "";
        this.arrivalHour = "";
        this.departureHour = "";
        this.routeIdForeignKey = 0L;
    }

    @Ignore
    public Station(String streetName, String stationName, String arrivalHour, String departureHour) {
        this.streetName = streetName;
        this.stationName = stationName;
        this.arrivalHour = arrivalHour;
        this.departureHour = departureHour;
    }

    @Ignore
    public Station(String streetName, String stationName, String arrivalHour, String departureHour, Long routeIdForeignKey) {
        this.streetName = streetName;
        this.stationName = stationName;
        this.arrivalHour = arrivalHour;
        this.departureHour = departureHour;
        this.routeIdForeignKey = routeIdForeignKey;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getArrivalHour() {
        return arrivalHour;
    }

    public void setArrivalHour(String arrivalHour) {
        this.arrivalHour = arrivalHour;
    }

    public String getDepartureHour() {
        return departureHour;
    }

    public void setDepartureHour(String departureHour) {
        this.departureHour = departureHour;
    }

    public Long getRouteIdForeignKey() {
        return routeIdForeignKey;
    }

    public void setRouteIdForeignKey(Long routeIdForeignKey) {
        this.routeIdForeignKey = routeIdForeignKey;
    }
}
