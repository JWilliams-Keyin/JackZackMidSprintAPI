package com.keyin.rest.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Flight findByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }

    public Flight getFlightById(long id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);

        return flightOptional.orElse(null);
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
