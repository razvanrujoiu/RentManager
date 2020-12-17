package com.example.urbanmobility.models;

import java.util.ArrayList;

public class MockRoute {
    private int stationsNo;
    private int estimatedRouteTime;
    private int routeNo;
    private ArrayList<MockStation> stations;

    public MockRoute(int stationsNo, int estimatedRouteTime, int routeNo, ArrayList<MockStation> stations) {
        this.stationsNo = stationsNo;
        this.estimatedRouteTime = estimatedRouteTime;
        this.routeNo = routeNo;
        this.stations = stations;
    }

    public int getStationsNo() {
        return stationsNo;
    }

    public int getRouteNo() {
        return routeNo;
    }

    public ArrayList<MockStation> getStations() {
        return stations;
    }

    public int getEstimatedRouteTime() {
        return estimatedRouteTime;
    }
}
