package com.keyin.rest.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateService {
    @Autowired
    private GateRepository gateRepository;

    public Gate findByGateNumber(String gateNumber) {
        return gateRepository.findByGateNumber(gateNumber);
    }

    public Gate getGateById(long id) {
        Optional<Gate> gateOptional = gateRepository.findById(id);

        return gateOptional.orElse(null);
    }

    public List<Gate> getAllGates() {
        return (List<Gate>) gateRepository.findAll();
    }

    public void deleteGateById(long id) {
        gateRepository.deleteById(id);
    }

    public Gate createGate(Gate newGate) {
        return gateRepository.save(newGate);
    }

    public Gate updateGate(long id, Gate updatedGate) {
        Optional<Gate> gateToUpdateOptional = gateRepository.findById(id);

        if (gateToUpdateOptional.isPresent()) {
            Gate gateToUpdate = gateToUpdateOptional.get();

            if (updatedGate.getGateNumber() != null) {
                gateToUpdate.setGateNumber(updatedGate.getGateNumber());
            }

            if (updatedGate.getGateAirport() != null) {
                gateToUpdate.setGateAirport(updatedGate.getGateAirport());
            }

            return gateRepository.save(gateToUpdate);
        }

        return null;
    }
}
