package com.keyin.rest.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return (List<City>) cityRepository.findAll();
    }

    public City getCityById(long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        return cityOptional.orElse(null);
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(long id, City updatedCity) {
        Optional<City> cityOptional = cityRepository.findById(id);

        if (cityOptional.isPresent()) {
            City cityToUpdate = cityOptional.get();

            cityToUpdate.setName(updatedCity.getName());
            cityToUpdate.setState(updatedCity.getState());
            cityToUpdate.setPopulation(updatedCity.getPopulation());
            cityToUpdate.setAirports(cityToUpdate.getAirports());

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

