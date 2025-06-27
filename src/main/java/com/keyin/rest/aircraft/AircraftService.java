package com.keyin.rest.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

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
        return aircraftRepository.save(newAircraft);
    }

    public Aircraft updateAircraft(long id, Aircraft updatedAircraft) {
        Optional<Aircraft> aircraftToUpdateOptional = aircraftRepository.findById(id);

        if (aircraftToUpdateOptional.isPresent()) {
            Aircraft aircraftToUpdate = aircraftToUpdateOptional.get();

            aircraftToUpdate.setAircraftType(updatedAircraft.getAircraftType());
            aircraftToUpdate.setAirlineName(updatedAircraft.getAirlineName());
            aircraftToUpdate.setNumberOfPassengers(updatedAircraft.getNumberOfPassengers());
            aircraftToUpdate.setAircraftPassengers(updatedAircraft.getAircraftPassengers());
            aircraftToUpdate.setAircraftAirports(updatedAircraft.getAircraftAirports());

            return aircraftRepository.save(aircraftToUpdate);
        }

        return null;
    }
}
