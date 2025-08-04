package com.keyin.rest.airport;

import com.keyin.rest.gate.Gate;
import com.keyin.rest.gate.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    @Autowired
    AirportRepository airportRepository;
    @Autowired
    GateRepository gateRepository;

    public Airport findByAirportName(String airportName) {
        return airportRepository.findByAirportName(airportName);
    }

    public Airport findByAirportCode(String airportCode) {
        return airportRepository.findByAirportCode(airportCode);
    }

    public List<Airport> getAllAirports() {
        return (List<Airport>) airportRepository.findAll();
    }

    public Airport getAirportById(long id) {
        Optional<Airport> airportOptional = airportRepository.findById(id);

        return airportOptional.orElse(null);
    }

    public void deleteAirportById(long id) {
        airportRepository.deleteById(id);
    }

    public Airport createAirport(Airport newAirport) {
        return airportRepository.save(newAirport);
    }

    public Airport updateAirport(long id, Airport updatedAirport) {
        Optional<Airport> airportToUpdateOptional = airportRepository.findById(id);

        if (airportToUpdateOptional.isPresent()) {
            Airport airportToUpdate = airportToUpdateOptional.get();

            if (updatedAirport.getAirportName() != null) {
                airportToUpdate.setAirportName(updatedAirport.getAirportName());
            }

            if (updatedAirport.getAirportCode() != null) {
                airportToUpdate.setAirportCode(updatedAirport.getAirportCode());
            }

            if (updatedAirport.getAirportCity() != null) {
                airportToUpdate.setAirportCity(updatedAirport.getAirportCity());
            }

            if (updatedAirport.getAirportGates() != null) {
                List<Gate> requestedGatesList = new ArrayList<>();

                for (Gate gate : updatedAirport.getAirportGates()) {
                    Gate requestedGate = gateRepository.findById(gate.getId())
                            .orElseThrow(() -> new RuntimeException("Gate not found"));
                    requestedGatesList.add(requestedGate);
                }
                airportToUpdate.setAirportGates(requestedGatesList);
            }

            return airportRepository.save(airportToUpdate);
        }

        return null;
    }
}
