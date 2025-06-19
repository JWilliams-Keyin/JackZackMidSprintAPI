package com.keyin.rest.aircraft;

import jakarta.persistence.*;

public class Aircraft {
    @Id
    @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "aircraft_sequence")
    private long id;

    public String aircraftType;
    public String airlineName;
    public int numberOfPassengers;

    public Aircraft() {
    }

    public Aircraft(long id) {
        this.id = id;
    }

    public Aircraft(String aircraftType, String airlineName) {
        this.aircraftType = aircraftType;
        this.airlineName = airlineName;
    }

    public long getId() {
        return id;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
