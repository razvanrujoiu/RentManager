package com.example.urbanmobility.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class RouteWithStations {
    @Embedded
    public Route route;
    @Relation(
            parentColumn = "routeId",
            entityColumn = "routeIdForeignKey"
    )

    public List<Station> stations;

    public RouteWithStations(Route route, List<Station> stations) {
        this.route = route;
        this.stations = stations;
    }
}
