package com.keyin.rest.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> getAllPassengers() {
        return (List<Passenger>) passengerRepository.findAll();
    }

    public Passenger getPassengerById(long id) {
        Optional<Passenger> passengerOptional = passengerRepository.findById(id);
        return passengerOptional.orElse(null);
    }

    public Passenger createPassenger(Passenger passenger) {
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
            passengerToUpdate.setAircraft(updatedPassenger.getAircraft());

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
