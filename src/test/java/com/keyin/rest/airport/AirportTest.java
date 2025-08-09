package com.keyin.rest.airport;

import com.keyin.rest.gate.Gate;
import com.keyin.rest.gate.GateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AirportTest {

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private GateRepository gateRepository;

    @InjectMocks
    private AirportService airportService;

    private Airport airport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        airport = new Airport("Calgary International", "YYC");
        airport.setId(1L);
    }

    @Test
    void testFindByAirportName() {
        when(airportRepository.findByAirportName("Calgary International")).thenReturn(airport);

        Airport found = airportService.findByAirportName("Calgary International");
        assertNotNull(found);
        assertEquals("YYC", found.getAirportCode());
        verify(airportRepository).findByAirportName("Calgary International");
    }

    @Test
    void testFindByAirportCode() {
        when(airportRepository.findByAirportCode("YYC")).thenReturn(airport);

        Airport found = airportService.findByAirportCode("YYC");
        assertNotNull(found);
        assertEquals("Calgary International", found.getAirportName());
        verify(airportRepository).findByAirportCode("YYC");
    }

    @Test
    void testGetAllAirports() {
        List<Airport> airportList = List.of(airport);
        when(airportRepository.findAll()).thenReturn(airportList);

        List<Airport> allAirports = airportService.getAllAirports();
        assertEquals(1, allAirports.size());
        verify(airportRepository).findAll();
    }

    @Test
    void testGetAirportById_Found() {
        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));

        Airport found = airportService.getAirportById(1L);
        assertNotNull(found);
        assertEquals("Calgary International", found.getAirportName());
        verify(airportRepository).findById(1L);
    }

    @Test
    void testGetAirportById_NotFound() {
        when(airportRepository.findById(2L)).thenReturn(Optional.empty());

        Airport found = airportService.getAirportById(2L);
        assertNull(found);
        verify(airportRepository).findById(2L);
    }

    @Test
    void testDeleteAirportById() {
        doNothing().when(airportRepository).deleteById(1L);

        airportService.deleteAirportById(1L);
        verify(airportRepository).deleteById(1L);
    }

    @Test
    void testCreateAirport() {
        when(airportRepository.save(airport)).thenReturn(airport);

        Airport created = airportService.createAirport(airport);
        assertNotNull(created);
        assertEquals("YYC", created.getAirportCode());
        verify(airportRepository).save(airport);
    }

    @Test
    void testUpdateAirport() {
        Airport updated = new Airport();
        updated.setAirportName("New Name");
        updated.setAirportCode("NNN");

        Gate gate = new Gate();
        gate.setId(1L);
        List<Gate> gates = new ArrayList<>();
        gates.add(gate);
        updated.setAirportGates(gates);

        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));
        when(gateRepository.findById(1L)).thenReturn(Optional.of(gate));
        when(airportRepository.save(any(Airport.class))).thenReturn(updated);

        Airport result = airportService.updateAirport(1L, updated);
        assertNotNull(result);
        assertEquals("New Name", result.getAirportName());
        assertEquals("NNN", result.getAirportCode());
        assertEquals(1, result.getAirportGates().size());

        verify(airportRepository).findById(1L);
        verify(gateRepository).findById(1L);
        verify(airportRepository).save(any(Airport.class));
    }

    @Test
    void testUpdateAirport_NotFound() {
        when(airportRepository.findById(1L)).thenReturn(Optional.empty());

        Airport updated = new Airport();
        Airport result = airportService.updateAirport(1L, updated);

        assertNull(result);
        verify(airportRepository).findById(1L);
        verify(airportRepository, never()).save(any());
    }
}
