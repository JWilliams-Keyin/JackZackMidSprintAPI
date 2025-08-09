package com.keyin.rest.city;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.keyin.rest.airport.Airport;
import com.keyin.rest.airport.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class CityTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private CityService cityService;

    private City city;
    private Airport airport;

    @BeforeEach
    void setUp() {
        airport = new Airport();
        airport.setId(1L);
        airport.setAirportName("Test Airport");
        airport.setAirportCode("TST");

        city = new City();
        city.setId(1L);
        city.setName("Test City");
        city.setState("TS");
        city.setPopulation(100000);
        city.setAirports(Collections.singletonList(airport));
    }

    @Test
    void testGetAllCities() {
        when(cityRepository.findAll()).thenReturn(Collections.singletonList(city));

        List<City> cities = cityService.getAllCities();

        assertNotNull(cities);
        assertEquals(1, cities.size());
        assertEquals("Test City", cities.get(0).getName());
    }

    @Test
    void testGetCityById_Found() {
        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        City foundCity = cityService.getCityById(1L);

        assertNotNull(foundCity);
        assertEquals("Test City", foundCity.getName());
    }

    @Test
    void testGetCityById_NotFound() {
        when(cityRepository.findById(2L)).thenReturn(Optional.empty());

        City foundCity = cityService.getCityById(2L);

        assertNull(foundCity);
    }

    @Test
    void testCreateCity_WithAirports() {
        when(airportRepository.findById(airport.getId())).thenReturn(Optional.of(airport));
        when(cityRepository.save(any(City.class))).thenReturn(city);

        City createdCity = cityService.createCity(city);

        assertNotNull(createdCity);
        assertEquals("Test City", createdCity.getName());
        verify(airportRepository, times(1)).findById(airport.getId());
        verify(cityRepository, times(1)).save(city);
    }

    @Test
    void testUpdateCity_AllFields() {
        City updatedCity = new City();
        updatedCity.setName("Updated City");
        updatedCity.setState("UP");
        updatedCity.setPopulation(200000);
        updatedCity.setAirports(Collections.singletonList(airport));

        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        when(airportRepository.findById(airport.getId())).thenReturn(Optional.of(airport));
        when(cityRepository.save(any(City.class))).thenReturn(updatedCity);

        City result = cityService.updateCity(1L, updatedCity);

        assertNotNull(result);
        assertEquals("Updated City", result.getName());
        assertEquals("UP", result.getState());
        assertEquals(200000, result.getPopulation());
        verify(airportRepository, times(1)).findById(airport.getId());
        verify(cityRepository, times(1)).save(city);
    }

    @Test
    void testUpdateCity_NotFound() {
        City updatedCity = new City();
        when(cityRepository.findById(99L)).thenReturn(Optional.empty());

        City result = cityService.updateCity(99L, updatedCity);

        assertNull(result);
    }

    @Test
    void testDeleteCity() {
        doNothing().when(cityRepository).deleteById(1L);

        cityService.deleteCity(1L);

        verify(cityRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindByName() {
        when(cityRepository.findByName("Test City")).thenReturn(city);

        City foundCity = cityService.findByName("Test City");

        assertNotNull(foundCity);
        assertEquals("Test City", foundCity.getName());
    }
}
