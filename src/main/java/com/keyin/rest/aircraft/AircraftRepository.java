package com.keyin.rest.aircraft;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
    public Aircraft findByAircraftType(String aircraftType);

    public Aircraft findByAirlineName(String airlineName);
}
