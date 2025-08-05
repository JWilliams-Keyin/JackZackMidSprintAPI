package com.keyin.rest.airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    @GetMapping("/airline")
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/aircraft/{id}")
    public Airline getAirlineById(@PathVariable long id) {
        return airlineService.getAirlineById(id);
    }

    @GetMapping("/airline_search")
    public List<Airline> searchAirlines(@RequestParam(value = "airline_name", required = false) String airlineName) {
        List<Airline> searchResults = new ArrayList<>();

        if (airlineName != null) {
            Airline airlineFound = airlineService.findByAirlineName(airlineName);

            searchResults.add(airlineFound);
        }

        return searchResults;
    }

    @PostMapping("/airline")
    public Airline createAirline(@RequestBody Airline airline) {
        return airlineService.createAirline(airline);
    }

    @PutMapping("/airline/{id}")
    public ResponseEntity<Airline> updateAirline(@PathVariable long id, @RequestBody Airline airline) {
        return ResponseEntity.ok(airlineService.updateAirline(id, airline));
    }

    @DeleteMapping("/airline/{id}")
    public void deleteAirlineById(@PathVariable long id) {
        airlineService.deleteAirlineById(id);
    }
}
