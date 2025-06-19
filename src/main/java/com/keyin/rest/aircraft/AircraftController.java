package com.keyin.rest.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/aircraft")
    public List<Aircraft> getAllAircrafts() {
        return aircraftService.getAllAircrafts();
    }

    @GetMapping("/aircraft/{id}")
    public Aircraft getAircraftById(@PathVariable long id) {
        return aircraftService.getAirCraftById(id);
    }

    @GetMapping("/aircraft_search")
    public List<Aircraft> searchAircrafts(@RequestParam(value = "aircraft_type", required = false) String aircraftType,
                                          @RequestParam(value = "airline_name", required = false) String airlineName) {
        List<Aircraft> searchResults = new ArrayList<Aircraft>();

        if (aircraftType != null) {
            Aircraft aircraftFound = aircraftService.findByAircraftType(aircraftType);

            searchResults.add(aircraftFound);
        } else if (airlineName != null) {
            Aircraft aircraftFound = aircraftService.findByAirlineName(airlineName);

            searchResults.add(aircraftFound);
        }

        return searchResults;
    }

    @PostMapping("/aircraft")
    public Aircraft createAircraft(@RequestBody Aircraft aircraft) {
        return aircraftService.createAircraft(aircraft);
    }

    @PutMapping("/aircraft/{id}")
    public ResponseEntity<Aircraft> updateAircraft(@PathVariable long id, @RequestBody Aircraft aircraft) {
        return ResponseEntity.ok(aircraftService.updateAircraft(id, aircraft));
    }

    @DeleteMapping("/aircraft/{id}")
    public void deleteAircraftById(@PathVariable long id) {
        aircraftService.deleteAircraftById(id);
    }
}
