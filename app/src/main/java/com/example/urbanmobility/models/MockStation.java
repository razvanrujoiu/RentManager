package com.example.urbanmobility.models;

public class MockStation {
    String name;
    String streetName;
    String arrivalHour;
    String departureHour;

    public MockStation(String name, String streetName, String arrivalHour, String departureHour) {
        this.name = name;
        this.streetName = streetName;
        this.arrivalHour = arrivalHour;
        this.departureHour = departureHour;
    }

    public String getName() {
        return name;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getArrivalHour() {
        return arrivalHour;
    }

    public String getDepartureHour() {
        return departureHour;
    }
}
