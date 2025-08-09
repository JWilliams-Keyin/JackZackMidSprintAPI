package com.keyin.rest.airline;

import com.keyin.rest.flight.Flight;
import com.keyin.rest.flight.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AirlineTest {

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private AirlineService airlineService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByAirlineName() {
        Airline airline = new Airline("TestAirline");
        when(airlineRepository.findByAirlineName("TestAirline")).thenReturn(airline);

        Airline found = airlineService.findByAirlineName("TestAirline");
        assertThat(found).isNotNull();
        assertThat(found.getAirlineName()).isEqualTo("TestAirline");
    }

    @Test
    void testGetAirlineById_Found() {
        Airline airline = new Airline("FoundAirline");
        when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));

        Airline found = airlineService.getAirlineById(1L);
        assertThat(found).isNotNull();
        assertThat(found.getAirlineName()).isEqualTo("FoundAirline");
    }

    @Test
    void testGetAirlineById_NotFound() {
        when(airlineRepository.findById(1L)).thenReturn(Optional.empty());

        Airline found = airlineService.getAirlineById(1L);
        assertThat(found).isNull();
    }

    @Test
    void testGetAllAirlines() {
        List<Airline> airlines = Arrays.asList(new Airline("A1"), new Airline("A2"));
        when(airlineRepository.findAll()).thenReturn(airlines);

        List<Airline> all = airlineService.getAllAirlines();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void testCreateAirline_WithFlights() {
        Flight flight = new Flight();
        flight.setId(10L);

        Airline airline = new Airline("CreateTest");
        airline.setAirlineFlights(Collections.singletonList(flight));

        when(flightRepository.findById(10L)).thenReturn(Optional.of(flight));
        when(airlineRepository.save(any(Airline.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Airline saved = airlineService.createAirline(airline);
        assertThat(saved).isNotNull();
        assertThat(saved.getAirlineName()).isEqualTo("CreateTest");
        assertThat(saved.getAirlineFlights().size()).isEqualTo(1);
    }

    @Test
    void testUpdateAirline_UpdateNameAndFlights() {
        Flight flight = new Flight();
        flight.setId(20L);

        Airline existingAirline = new Airline("OldName");

        Airline updatedAirline = new Airline("NewName");
        updatedAirline.setAirlineFlights(Collections.singletonList(flight));

        when(airlineRepository.findById(1L)).thenReturn(Optional.of(existingAirline));
        when(flightRepository.findById(20L)).thenReturn(Optional.of(flight));
        when(airlineRepository.save(any(Airline.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Airline result = airlineService.updateAirline(1L, updatedAirline);
        assertThat(result).isNotNull();
        assertThat(result.getAirlineName()).isEqualTo("NewName");
        assertThat(result.getAirlineFlights().size()).isEqualTo(1);
    }

    @Test
    void testDeleteAirlineById() {
        doNothing().when(airlineRepository).deleteById(1L);
        airlineService.deleteAirlineById(1L);
        verify(airlineRepository, times(1)).deleteById(1L);
    }
}
