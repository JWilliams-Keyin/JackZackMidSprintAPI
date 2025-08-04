package com.keyin.rest.airline;

import com.keyin.rest.flight.Flight;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Airline {
    @Id
    @SequenceGenerator(name = "airline_sequence", sequenceName = "airline_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "airline_sequence")
    private long id;

    private String airlineName;

    @OneToMany
    private List<Flight> airlineFlights;

    public Airline() {

    }

    public Airline(long id) {
        this.id = id;
    }

    public Airline(String airlineName) {
        this.airlineName = airlineName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public List<Flight> getAirlineFlights() {
        return airlineFlights;
    }

    public void setAirlineFlights(List<Flight> airlineFlights) {
        this.airlineFlights = airlineFlights;
    }
}
