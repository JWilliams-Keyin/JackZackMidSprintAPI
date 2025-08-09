package com.keyin.rest.aircraft;

import com.keyin.rest.airport.Airport;
import com.keyin.rest.flight.Flight;
import com.keyin.rest.passenger.Passenger;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class   Aircraft {
    @Id
    @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "aircraft_sequence")
    private long id;

    public String aircraftType;
    public String airlineName;
    public int numberOfPassengers;

    @OneToMany
    private List<Passenger> aircraftPassengers;

    @OneToMany
    private List<Flight> aircraftFlights;

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

    public List<Passenger> getAircraftPassengers() {
        return aircraftPassengers;
    }

    public List<Flight> getAircraftFlights() {
        return aircraftFlights;
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

    public void setAircraftPassengers(List<Passenger> aircraftPassengers) {
        this.aircraftPassengers = aircraftPassengers;
    }

    public void setAircraftFlights(List<Flight> aircraftFlights) {
        this.aircraftFlights = aircraftFlights;
    }
}
