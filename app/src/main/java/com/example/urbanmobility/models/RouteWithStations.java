package com.example.urbanmobility.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RouteWithStations {

    @SerializedName("route")
    @Expose
    @Embedded
    public Route route;
    @Relation(
            parentColumn = "routeId",
            entityColumn = "routeIdForeignKey"
    )

    @SerializedName("stationList")
    @Expose
    public List<Station> stations;

    public RouteWithStations(Route route, List<Station> stations) {
        this.route = route;
        this.stations = stations;
    }
}
