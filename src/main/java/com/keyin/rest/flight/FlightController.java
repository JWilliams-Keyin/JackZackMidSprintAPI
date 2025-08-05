package com.keyin.rest.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/flight")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/flight/{id}")
    public Flight getFlightById(@PathVariable long id) {
        return flightService.getFlightById(id);
    }

    @GetMapping("/flight_search")
    public List<Flight> searchFlights(@RequestParam(value = "flight_number", required = false) String flightNumber) {
        List<Flight> searchResults = new ArrayList<>();

        if (flightNumber != null) {
            Flight flightFound = flightService.findByFlightNumber(flightNumber);

            searchResults.add(flightFound);
        }

        return searchResults;
    }

    @PostMapping("/flight")
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PutMapping("/flight/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable long id, @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.updateFlight(id, flight));
    }

    @DeleteMapping("/flight/{id}")
    public void deleteFlightById(@PathVariable long id) {
        flightService.deleteFlightById(id);
    }
}
