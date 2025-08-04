package com.keyin.rest.airport;

import com.keyin.rest.city.City;
import com.keyin.rest.gate.Gate;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Airport {
    @Id
    @SequenceGenerator(name = "airport_sequence", sequenceName = "airport_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "airport_sequence")
    private long id;

    public String airportName;
    public String airportCode;

    @OneToOne
    private City airportCity;
    @OneToMany
    private List<Gate> airportGates;

    public Airport() {

    }

    public Airport(long id) {
        this.id = id;
    }

    public Airport(String airportName, String airportCode) {
        this.airportName = airportName;
        this.airportCode = airportCode;
    }

    public long getId() {
        return id;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public City getAirportCity() {
        return airportCity;
    }

    public List<Gate> getAirportGates() {
        return airportGates;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public void setAirportCity(City airportCity) {
        this.airportCity = airportCity;
    }

    public void setAirportGates(List<Gate> airportGates) {
        this.airportGates = airportGates;
    }
}
