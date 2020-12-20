package com.example.urbanmobility.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RouteList {

    @SerializedName("routes")
    @Expose
    private ArrayList<RouteWithStations> routes = null;

    public ArrayList<RouteWithStations> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<RouteWithStations> routes) {
        this.routes = routes;
    }
}
