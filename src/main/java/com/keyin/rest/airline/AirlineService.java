package com.keyin.rest.airline;

import com.keyin.rest.flight.Flight;
import com.keyin.rest.flight.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private FlightRepository flightRepository;

    public Airline findByAirlineName(String airlineName) {
        return airlineRepository.findByAirlineName(airlineName);
    }

    public List<Airline> getAllAirlines() {
        return (List<Airline>) airlineRepository.findAll();
    }

    public void deleteAirlineById(long id) {
        airlineRepository.deleteById(id);
    }

    public Airline createAirline(Airline newAirline) {
        if (newAirline.getAirlineFlights() != null) {
            List<Flight> requestedFlightsList = new ArrayList<>();

            for (Flight flight : newAirline.getAirlineFlights()) {
                Flight requestedFlight = flightRepository.findById(flight.getId())
                        .orElseThrow(() -> new RuntimeException("Flight not found"));
                requestedFlightsList.add(requestedFlight);
            }
            newAirline.setAirlineFlights(requestedFlightsList);
        }

        return airlineRepository.save(newAirline);
    }

    public Airline updateAirline(long id, Airline updatedAirline) {
        Optional<Airline> airlineToUpdateOptional = airlineRepository.findById(id);

        if (airlineToUpdateOptional.isPresent()) {
            Airline airlineToUpdate = airlineToUpdateOptional.get();

            if (updatedAirline.getAirlineName() != null) {
                airlineToUpdate.setAirlineName(updatedAirline.getAirlineName());
            } else if (updatedAirline.getAirlineFlights() != null) {
                List<Flight> requestedFlightsList = new ArrayList<>();

                for (Flight flight : updatedAirline.getAirlineFlights()) {
                    Flight requestedFlight = flightRepository.findById(flight.getId())
                            .orElseThrow(() -> new RuntimeException("Flight not found"));
                    requestedFlightsList.add(requestedFlight);
                }
                airlineToUpdate.setAirlineFlights(requestedFlightsList);
            }

            return airlineRepository.save(airlineToUpdate);
        }

        return null;
    }
}
