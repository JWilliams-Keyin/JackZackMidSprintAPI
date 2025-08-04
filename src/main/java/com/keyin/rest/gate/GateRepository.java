package com.keyin.rest.gate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateRepository extends CrudRepository<Gate, Long> {
    public Gate findByGateNumber(String gateNumber);
}
