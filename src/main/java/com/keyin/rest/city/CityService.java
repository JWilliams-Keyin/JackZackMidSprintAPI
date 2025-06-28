package com.keyin.rest.city;

import com.keyin.rest.airport.Airport;
import com.keyin.rest.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AirportRepository airportRepository;

    public List<City> getAllCities() {
        return (List<City>) cityRepository.findAll();
    }

    public City getCityById(long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        return cityOptional.orElse(null);
    }

    public City createCity(City city) {
        List<Airport> requestedAirportsList = new ArrayList<>();

        for (Airport airport : city.getAirports()) {
            Airport requestedAirport = airportRepository.findById(airport.getId())
                    .orElseThrow(() -> new RuntimeException("Airport not found"));
            requestedAirportsList.add(requestedAirport);
        }
        city.setAirports(requestedAirportsList);

        return cityRepository.save(city);
    }

    public City updateCity(long id, City updatedCity) {
        Optional<City> cityOptional = cityRepository.findById(id);

        if (cityOptional.isPresent()) {
            City cityToUpdate = cityOptional.get();

            cityToUpdate.setName(updatedCity.getName());
            cityToUpdate.setState(updatedCity.getState());
            cityToUpdate.setPopulation(updatedCity.getPopulation());

            List<Airport> requestedAirportsList = new ArrayList<>();

            for (Airport airport : cityOptional.get().getAirports()) {
                Airport requestedAirport = airportRepository.findById(airport.getId())
                        .orElseThrow(() -> new RuntimeException("Airport not found"));
                requestedAirportsList.add(requestedAirport);
            }
            cityToUpdate.setAirports(requestedAirportsList);

            return cityRepository.save(cityToUpdate);
        }

        return null;
    }

    public void deleteCity(long id) {
        cityRepository.deleteById(id);
    }

    public City findByName(String name) {
        return cityRepository.findByName(name);
    }
}

