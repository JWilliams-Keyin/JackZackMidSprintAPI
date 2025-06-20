package com.keyin.rest.airport;

import com.keyin.rest.aircraft.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/airport")
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/airport/{id}")
    public Airport getAirportById(@PathVariable long id) {
        return airportService.getAirportById(id);
    }

    @GetMapping("/airport_search")
    public List<Airport> searchAirports(@RequestParam(value = "airport_name", required = false) String airportName,
                                        @RequestParam(value = "airport_code", required = false) String airportCode) {
        List<Airport> searchResults = new ArrayList<Airport>();

        if (airportName != null) {
            Airport airportFound = airportService.findByAirportName(airportName);

            searchResults.add(airportFound);
        } else if (airportCode != null) {
            Airport airportFound = airportService.findByAirportCode(airportCode);

            searchResults.add(airportFound);
        }

        return searchResults;
    }
}
