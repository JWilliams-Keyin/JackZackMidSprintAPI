package com.keyin.rest.flight;

import com.keyin.rest.aircraft.Aircraft;
import com.keyin.rest.aircraft.AircraftRepository;
import com.keyin.rest.airport.Airport;
import com.keyin.rest.airport.AirportRepository;
import com.keyin.rest.gate.Gate;
import com.keyin.rest.gate.GateRepository;
import com.keyin.rest.airline.Airline;
import com.keyin.rest.airline.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AircraftRepository aircraftRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private GateRepository gateRepository;
    @Autowired
    private AirlineRepository airlineRepository;

    public Flight findByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }

    public List<Flight> getAllFlights() {
        return (List<Flight>) flightRepository.findAll();
    }

    public void deleteFlightById(long id) {
        flightRepository.deleteById(id);
    }

    public Flight createFlight(Flight newFlight) {
        return flightRepository.save(newFlight);
    }

    public Flight updateFlight(long id, Flight updatedFlight) {
        Optional<Flight> flightToUpdateOptional = flightRepository.findById(id);

        if (flightToUpdateOptional.isPresent()) {
            Flight flightToUpdate = flightToUpdateOptional.get();

            if (updatedFlight.getFlightNumber() != null) {
                flightToUpdate.setFlightNumber(updatedFlight.getFlightNumber());
            }

            if (updatedFlight.getFlightAircraft() != null) {
                flightToUpdate.setFlightAircraft(updatedFlight.getFlightAircraft());
            }

            if (updatedFlight.getInitialAirport() != null) {
                flightToUpdate.setInitialAirport(updatedFlight.getInitialAirport());
            }

            if (updatedFlight.getDestinationAirport() != null) {
                flightToUpdate.setDestinationAirport(updatedFlight.getDestinationAirport());
            }

            if (updatedFlight.getDestinationGate() != null) {
                flightToUpdate.setDestinationGate(updatedFlight.getDestinationGate());
            }

            if (updatedFlight.getFlightAirline() != null) {
                flightToUpdate.setFlightAirline(updatedFlight.getFlightAirline());
            }

            return flightRepository.save(flightToUpdate);
        }

        return null;
    }
}
