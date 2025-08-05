package com.keyin.rest.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class GateController {
    @Autowired
    private GateService gateService;

    @GetMapping("/gate")
    public List<Gate> getAllGates() {
        return gateService.getAllGates();
    }

    @GetMapping("/gate/{id}")
    public Gate getGateById(@PathVariable long id) {
        return gateService.getGateById(id);
    }

    @GetMapping("/gate_search")
    public List<Gate> searchGates(@RequestParam(value = "gate_number", required = false) String gateNumber) {
        List<Gate> searchResults = new ArrayList<>();

        if (gateNumber != null) {
            Gate gateFound = gateService.findByGateNumber(gateNumber);

            searchResults.add(gateFound);
        }

        return searchResults;
    }

    @PostMapping("/gate")
    public Gate createGate(@RequestBody Gate gate) {
        return gateService.createGate(gate);
    }

    @PutMapping("/gate/{id}")
    public ResponseEntity<Gate> updateGate(@PathVariable long id, @RequestBody Gate gate) {
        return ResponseEntity.ok(gateService.updateGate(id, gate));
    }

    @DeleteMapping("/gate/{id}")
    public void deleteGateById(@PathVariable long id) {
        gateService.deleteGateById(id);
    }
}
