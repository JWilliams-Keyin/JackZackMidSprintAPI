package com.keyin.rest.gate;

import com.keyin.rest.airport.Airport;
import jakarta.persistence.*;

@Entity
public class Gate {
    @Id
    @SequenceGenerator(name = "gate_sequence", sequenceName = "gate_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "gate_sequence")
    private long id;

    private String gateNumber;

    @ManyToOne
    private Airport gateAirport;

    public Gate() {

    }

    public Gate(long id) {
        this.id = id;
    }

    public Gate(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public Airport getGateAirport() {
        return gateAirport;
    }

    public void setGateAirport(Airport gateAirport) {
        this.gateAirport = gateAirport;
    }
}
