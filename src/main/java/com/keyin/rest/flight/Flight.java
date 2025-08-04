package com.keyin.rest.flight;

import com.keyin.rest.airline.Airline;
import com.keyin.rest.airport.Airport;
import com.keyin.rest.aircraft.Aircraft;
import com.keyin.rest.gate.Gate;
import jakarta.persistence.*;

@Entity
public class Flight {
    @Id
    @SequenceGenerator(name = "flight_sequence", sequenceName = "flight_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "flight_sequence")
    private long id;

    private String flightNumber;

    @ManyToOne
    private Aircraft flightAircraft;
    @ManyToOne
    private Airport initialAirport;
    @ManyToOne
    private Airport destinationAirport;
    @ManyToOne
    private Gate destinationGate;
    @ManyToOne
    private Airline flightAirline;

    public Flight() {
    }

    public Flight(long id) {
        this.id = id;
    }

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Aircraft getFlightAircraft() {
        return flightAircraft;
    }

    public void setFlightAircraft(Aircraft flightAircraft) {
        this.flightAircraft = flightAircraft;
    }

    public Airport getInitialAirport() {
        return initialAirport;
    }

    public void setInitialAirport(Airport initialAirport) {
        this.initialAirport = initialAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Gate getDestinationGate() {
        return destinationGate;
    }

    public void setDestinationGate(Gate destinationGate) {
        this.destinationGate = destinationGate;
    }

    public Airline getFlightAirline() {
        return flightAirline;
    }

    public void setFlightAirline(Airline flightAirline) {
        this.flightAirline = flightAirline;
    }
}
