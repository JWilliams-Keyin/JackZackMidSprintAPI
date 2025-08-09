package com.keyin.rest.aircraft;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AircraftTest {

    private MockMvc mockMvc;

    @Mock
    private AircraftService aircraftService;

    @InjectMocks
    private AircraftController aircraftController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Aircraft sampleAircraft;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(aircraftController).build();

        sampleAircraft = new Aircraft("Boeing 737", "Air Canada");
        sampleAircraft.setId(1L);
        sampleAircraft.setNumberOfPassengers(150);
    }

    @Test
    public void testGetAllAircrafts() throws Exception {
        List<Aircraft> aircraftList = Arrays.asList(sampleAircraft);
        when(aircraftService.getAllAircrafts()).thenReturn(aircraftList);

        mockMvc.perform(get("/aircraft"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].aircraftType", is("Boeing 737")))
                .andExpect(jsonPath("$[0].airlineName", is("Air Canada")))
                .andExpect(jsonPath("$[0].numberOfPassengers", is(150)));
    }

    @Test
    public void testGetAircraftById() throws Exception {
        when(aircraftService.getAirCraftById(1L)).thenReturn(sampleAircraft);

        mockMvc.perform(get("/aircraft/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.aircraftType", is("Boeing 737")))
                .andExpect(jsonPath("$.airlineName", is("Air Canada")));
    }

    @Test
    public void testSearchByAircraftType() throws Exception {
        when(aircraftService.findByAircraftType("Boeing 737")).thenReturn(sampleAircraft);

        mockMvc.perform(get("/aircraft_search")
                        .param("aircraft_type", "Boeing 737"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aircraftType", is("Boeing 737")));
    }

    @Test
    public void testSearchByAirlineName() throws Exception {
        when(aircraftService.findByAirlineName("Air Canada")).thenReturn(sampleAircraft);

        mockMvc.perform(get("/aircraft_search")
                        .param("airline_name", "Air Canada"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].airlineName", is("Air Canada")));
    }

    @Test
    public void testCreateAircraft() throws Exception {
        when(aircraftService.createAircraft(any(Aircraft.class))).thenReturn(sampleAircraft);

        String json = objectMapper.writeValueAsString(sampleAircraft);

        mockMvc.perform(post("/aircraft")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aircraftType", is("Boeing 737")))
                .andExpect(jsonPath("$.airlineName", is("Air Canada")));
    }

    @Test
    public void testUpdateAircraft() throws Exception {
        when(aircraftService.updateAircraft(eq(1L), any(Aircraft.class))).thenReturn(sampleAircraft);

        String json = objectMapper.writeValueAsString(sampleAircraft);

        mockMvc.perform(put("/aircraft/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.aircraftType", is("Boeing 737")));
    }

    @Test
    public void testDeleteAircraft() throws Exception {
        // For void methods, just mock to do nothing (default behavior)
        mockMvc.perform(delete("/aircraft/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test //Fail Test
    public void testGetAircraftById_Fail() throws Exception {
        when(aircraftService.getAirCraftById(1L)).thenReturn(sampleAircraft);

        mockMvc.perform(get("/aircraft/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aircraftType", is("Airbus A320"))); // This will fail
    }

}
