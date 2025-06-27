package com.keyin.rest.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    @Autowired
    AirportRepository airportRepository;

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

            airportToUpdate.setAirportName(updatedAirport.getAirportName());
            airportToUpdate.setAirportCode(updatedAirport.getAirportCode());
            airportToUpdate.setAirportCity(updatedAirport.getAirportCity());

            return airportRepository.save(airportToUpdate);
        }

        return null;
    }
}
