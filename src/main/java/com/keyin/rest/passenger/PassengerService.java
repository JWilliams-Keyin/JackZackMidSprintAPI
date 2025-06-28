package com.keyin.rest.passenger;

import com.keyin.rest.aircraft.Aircraft;
import com.keyin.rest.airport.Airport;
import com.keyin.rest.aircraft.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Passenger> getAllPassengers() {
        return (List<Passenger>) passengerRepository.findAll();
    }

    public Passenger getPassengerById(long id) {
        Optional<Passenger> passengerOptional = passengerRepository.findById(id);
        return passengerOptional.orElse(null);
    }

    public Passenger createPassenger(Passenger passenger) {
        if (passenger.getAircraft() != null) {
            List<Aircraft> requestedAircraftList = new ArrayList<>();

            for (Aircraft aircraft : passenger.getAircraft()) {
                Aircraft requestedAircraft = aircraftRepository.findById(aircraft.getId())
                        .orElseThrow(() -> new RuntimeException("Aircraft not found"));
                requestedAircraftList.add(requestedAircraft);
            }
            passenger.setAircraft(requestedAircraftList);
        }

        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(long id, Passenger updatedPassenger) {
        Optional<Passenger> passengerOptional = passengerRepository.findById(id);

        if (passengerOptional.isPresent()) {
            Passenger passengerToUpdate = passengerOptional.get();

            passengerToUpdate.setFirstName(updatedPassenger.getFirstName());
            passengerToUpdate.setLastName(updatedPassenger.getLastName());
            passengerToUpdate.setPhoneNumber(updatedPassenger.getPhoneNumber());
            passengerToUpdate.setCity(updatedPassenger.getCity());

            List<Aircraft> requestedAircraftList = new ArrayList<>();

            for (Aircraft aircraft : passengerOptional.get().getAircraft()) {
                Aircraft requestedAircraft = aircraftRepository.findById(aircraft.getId())
                        .orElseThrow(() -> new RuntimeException("Aircraft not found"));
                requestedAircraftList.add(requestedAircraft);
            }
            passengerToUpdate.setAircraft(requestedAircraftList);

            return passengerRepository.save(passengerToUpdate);
        }

        return null;
    }

    public void deletePassenger(long id) {
        passengerRepository.deleteById(id);
    }

    public Passenger findByFirstName(String firstName) {
        return passengerRepository.findByFirstName(firstName);
    }

    public Passenger findByLastName(String lastName) {
        return passengerRepository.findByLastName(lastName);
    }
}
