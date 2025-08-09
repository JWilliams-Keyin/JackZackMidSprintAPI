package com.keyin.rest.passenger;

import com.keyin.rest.aircraft.Aircraft;
import com.keyin.rest.aircraft.AircraftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByFirstName() {
        Passenger passenger = new Passenger("John", "Doe", "123456789");
        when(passengerRepository.findByFirstName("John")).thenReturn(passenger);

        Passenger found = passengerService.findByFirstName("John");

        assertNotNull(found);
        assertEquals("John", found.getFirstName());
    }

    @Test
    void testGetPassengerByIdFound() {
        Passenger passenger = new Passenger("Jane", "Smith", "987654321");
        passenger.setId(1L);
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passenger));

        Passenger found = passengerService.getPassengerById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testGetPassengerByIdNotFound() {
        when(passengerRepository.findById(1L)).thenReturn(Optional.empty());

        Passenger found = passengerService.getPassengerById(1L);

        assertNull(found);
    }

    @Test
    void testGetAllPassengers() {
        Passenger p1 = new Passenger("Alice", "Johnson", "111222333");
        Passenger p2 = new Passenger("Bob", "Lee", "444555666");
        when(passengerRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Passenger> passengers = passengerService.getAllPassengers();

        assertEquals(2, passengers.size());
    }

    @Test
    void testCreatePassengerWithAircraft() {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(aircraft);

        Passenger passenger = new Passenger("Tim", "Cook", "000111222");
        passenger.setAircraft(aircraftList);

        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraft));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);

        Passenger saved = passengerService.createPassenger(passenger);

        assertNotNull(saved);
        assertEquals("Tim", saved.getFirstName());
        verify(aircraftRepository).findById(1L);
        verify(passengerRepository).save(any(Passenger.class));
    }

    @Test
    void testUpdatePassenger() {
        Passenger existingPassenger = new Passenger("Old", "Name", "123");
        existingPassenger.setId(1L);

        Passenger updatedPassenger = new Passenger("New", "Name", "456");

        when(passengerRepository.findById(1L)).thenReturn(Optional.of(existingPassenger));
        when(aircraftRepository.findById(anyLong())).thenReturn(Optional.of(new Aircraft())); // Assume empty aircraft list in this test
        when(passengerRepository.save(any(Passenger.class))).thenReturn(existingPassenger);

        Passenger result = passengerService.updatePassenger(1L, updatedPassenger);

        assertNotNull(result);
        assertEquals("New", result.getFirstName());
        assertEquals("Name", result.getLastName());
        assertEquals("456", result.getPhoneNumber());
    }

    @Test
    void testDeletePassenger() {
        doNothing().when(passengerRepository).deleteById(1L);

        passengerService.deletePassenger(1L);

        verify(passengerRepository, times(1)).deleteById(1L);
    }
}
