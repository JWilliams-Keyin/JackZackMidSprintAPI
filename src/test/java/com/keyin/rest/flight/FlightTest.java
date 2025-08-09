package com.keyin.rest.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FlightTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    private Flight flight1;

    @BeforeEach
    void setUp() {
        flight1 = new Flight();
        flight1.setId(1L);
        flight1.setFlightNumber("FL123");
    }

    @Test
    void testFindByFlightNumber() {
        when(flightRepository.findByFlightNumber("FL123")).thenReturn(flight1);

        Flight result = flightService.findByFlightNumber("FL123");
        assertNotNull(result);
        assertEquals("FL123", result.getFlightNumber());

        verify(flightRepository).findByFlightNumber("FL123");
    }

    @Test
    void testGetFlightByIdFound() {
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight1));

        Flight result = flightService.getFlightById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(flightRepository).findById(1L);
    }

    @Test
    void testGetFlightByIdNotFound() {
        when(flightRepository.findById(2L)).thenReturn(Optional.empty());

        Flight result = flightService.getFlightById(2L);
        assertNull(result);

        verify(flightRepository).findById(2L);
    }

    @Test
    void testGetAllFlights() {
        when(flightRepository.findAll()).thenReturn(List.of(flight1));

        List<Flight> flights = flightService.getAllFlights();
        assertFalse(flights.isEmpty());
        assertEquals(1, flights.size());

        verify(flightRepository).findAll();
    }

    @Test
    void testCreateFlight() {
        when(flightRepository.save(flight1)).thenReturn(flight1);

        Flight created = flightService.createFlight(flight1);
        assertNotNull(created);
        assertEquals("FL123", created.getFlightNumber());

        verify(flightRepository).save(flight1);
    }

    @Test
    void testDeleteFlight() {
        doNothing().when(flightRepository).deleteById(1L);

        flightService.deleteFlightById(1L);

        verify(flightRepository).deleteById(1L);
    }

    @Test
    void testUpdateFlightFound() {
        Flight updatedFlight = new Flight();
        updatedFlight.setFlightNumber("FL999");

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight1));
        when(flightRepository.save(any(Flight.class))).thenAnswer(i -> i.getArgument(0));

        Flight result = flightService.updateFlight(1L, updatedFlight);

        assertNotNull(result);
        assertEquals("FL999", result.getFlightNumber());

        verify(flightRepository).findById(1L);
        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    void testUpdateFlightNotFound() {
        Flight updatedFlight = new Flight();
        updatedFlight.setFlightNumber("FL999");

        when(flightRepository.findById(2L)).thenReturn(Optional.empty());

        Flight result = flightService.updateFlight(2L, updatedFlight);

        assertNull(result);

        verify(flightRepository).findById(2L);
        verify(flightRepository, never()).save(any());
    }
}
