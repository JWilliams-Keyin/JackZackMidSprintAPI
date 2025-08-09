package com.keyin.rest.gate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GateTest {

    private GateRepository gateRepository;
    private GateService gateService;

    @BeforeEach
    void setUp() throws Exception {
        gateRepository = Mockito.mock(GateRepository.class);
        gateService = new GateService();
        Field field = GateService.class.getDeclaredField("gateRepository");
        field.setAccessible(true);
        field.set(gateService, gateRepository);
    }

    @Test
    void testFindByGateNumber() {
        Gate gate = new Gate();
        gate.setId(1L);
        gate.setGateNumber("A1");

        when(gateRepository.findByGateNumber("A1")).thenReturn(gate);

        Gate result = gateService.findByGateNumber("A1");

        assertNotNull(result);
        assertEquals("A1", result.getGateNumber());
        verify(gateRepository).findByGateNumber("A1");
    }

    @Test
    void testGetGateById_Found() {
        Gate gate = new Gate();
        gate.setId(2L);

        when(gateRepository.findById(2L)).thenReturn(Optional.of(gate));

        Gate result = gateService.getGateById(2L);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        verify(gateRepository).findById(2L);
    }

    @Test
    void testGetGateById_NotFound() {
        when(gateRepository.findById(3L)).thenReturn(Optional.empty());

        Gate result = gateService.getGateById(3L);

        assertNull(result);
        verify(gateRepository).findById(3L);
    }

    @Test
    void testGetAllGates() {
        Gate gate1 = new Gate();
        gate1.setId(1L);
        Gate gate2 = new Gate();
        gate2.setId(2L);

        when(gateRepository.findAll()).thenReturn(List.of(gate1, gate2));

        List<Gate> gates = gateService.getAllGates();

        assertEquals(2, gates.size());
        verify(gateRepository).findAll();
    }

    @Test
    void testCreateGate() {
        Gate newGate = new Gate();
        newGate.setGateNumber("B2");

        when(gateRepository.save(newGate)).thenReturn(newGate);

        Gate result = gateService.createGate(newGate);

        assertNotNull(result);
        assertEquals("B2", result.getGateNumber());
        verify(gateRepository).save(newGate);
    }

    @Test
    void testUpdateGate_Found() {
        Gate existingGate = new Gate();
        existingGate.setId(1L);
        existingGate.setGateNumber("C3");

        Gate updatedGate = new Gate();
        updatedGate.setGateNumber("C4");

        when(gateRepository.findById(1L)).thenReturn(Optional.of(existingGate));
        when(gateRepository.save(existingGate)).thenReturn(existingGate);

        Gate result = gateService.updateGate(1L, updatedGate);

        assertNotNull(result);
        assertEquals("C4", result.getGateNumber());
        verify(gateRepository).findById(1L);
        verify(gateRepository).save(existingGate);
    }

    @Test
    void testUpdateGate_NotFound() {
        Gate updatedGate = new Gate();
        updatedGate.setGateNumber("D5");

        when(gateRepository.findById(5L)).thenReturn(Optional.empty());

        Gate result = gateService.updateGate(5L, updatedGate);

        assertNull(result);
        verify(gateRepository).findById(5L);
        verify(gateRepository, never()).save(any());
    }

    @Test
    void testDeleteGateById() {
        doNothing().when(gateRepository).deleteById(10L);

        gateService.deleteGateById(10L);

        verify(gateRepository).deleteById(10L);
    }
}
