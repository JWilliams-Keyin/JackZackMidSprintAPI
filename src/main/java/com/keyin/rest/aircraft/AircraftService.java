package com.keyin.rest.aircraft;

import com.keyin.rest.airport.Airport;
import com.keyin.rest.flight.Flight;
import com.keyin.rest.flight.FlightRepository;
import com.keyin.rest.passenger.Passenger;
import com.keyin.rest.passenger.PassengerRepository;
import com.keyin.rest.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private FlightRepository flightRepository;

    public Aircraft findByAircraftType(String aircraftType) {
        return aircraftRepository.findByAircraftType(aircraftType);
    }

    public Aircraft findByAirlineName(String airlineName) {
        return aircraftRepository.findByAirlineName(airlineName);
    }

    public List<Aircraft> getAllAircrafts() {
        return (List<Aircraft>) aircraftRepository.findAll();
    }

    public Aircraft getAirCraftById(long id) {
        Optional<Aircraft> aircraftOptional = aircraftRepository.findById(id);

        return aircraftOptional.orElse(null);
    }

    public void deleteAircraftById(long id) {
        aircraftRepository.deleteById(id);
    }

    public Aircraft createAircraft(Aircraft newAircraft) {
        if (newAircraft.getAircraftPassengers() != null) {
            List<Passenger> requestedPassengersList = new ArrayList<>();

            for (Passenger passenger : newAircraft.getAircraftPassengers()) {
                Passenger requestedPassenger = passengerRepository.findById(passenger.getId())
                        .orElseThrow(() -> new RuntimeException("Passenger not found"));
                requestedPassengersList.add(requestedPassenger);
            }
            newAircraft.setAircraftPassengers(requestedPassengersList);
        }

        if (newAircraft.getAircraftFlights() != null) {
            List<Flight> requestedFlightsList = new ArrayList<>();

            for (Flight flight : newAircraft.getAircraftFlights()) {
                Flight requestedFlight = flightRepository.findById(flight.getId())
                        .orElseThrow(() -> new RuntimeException("Flight not found"));
                requestedFlightsList.add(requestedFlight);
            }
            newAircraft.setAircraftFlights(requestedFlightsList);
        }

        return aircraftRepository.save(newAircraft);
    }

    public Aircraft updateAircraft(long id, Aircraft updatedAircraft) {
        Optional<Aircraft> aircraftToUpdateOptional = aircraftRepository.findById(id);

        if (aircraftToUpdateOptional.isPresent()) {
            Aircraft aircraftToUpdate = aircraftToUpdateOptional.get();

            if (updatedAircraft.getAircraftType() != null) {
                aircraftToUpdate.setAircraftType(updatedAircraft.getAircraftType());
            }

            if (updatedAircraft.getAirlineName() != null) {
                aircraftToUpdate.setAirlineName(updatedAircraft.getAirlineName());
            }

            if (updatedAircraft.getNumberOfPassengers() != 0) {
                aircraftToUpdate.setNumberOfPassengers(updatedAircraft.getNumberOfPassengers());
            }

            if (updatedAircraft.getAircraftPassengers() != null) {
                List<Passenger> requestedPassengersList = new ArrayList<>();

                for (Passenger passenger : aircraftToUpdateOptional.get().getAircraftPassengers()) {
                    Passenger requestedPassenger = passengerRepository.findById(passenger.getId())
                            .orElseThrow(() -> new RuntimeException("Passenger not found"));
                    requestedPassengersList.add(requestedPassenger);
                }
                aircraftToUpdate.setAircraftPassengers(requestedPassengersList);
            }

            if (updatedAircraft.getAircraftFlights() != null) {
                List<Flight> requestedFlightsList = new ArrayList<>();

                for (Flight flight : aircraftToUpdate.getAircraftFlights()) {
                    Flight requestedFlight = flightRepository.findById(flight.getId())
                            .orElseThrow(() -> new RuntimeException("Flight not found"));
                    requestedFlightsList.add(requestedFlight);
                }
                aircraftToUpdate.setAircraftFlights(requestedFlightsList);
            }

            return aircraftRepository.save(aircraftToUpdate);
        }

        return null;
    }
}
