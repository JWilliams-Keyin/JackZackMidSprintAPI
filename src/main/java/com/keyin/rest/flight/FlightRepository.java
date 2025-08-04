package com.keyin.rest.flight;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
    public Flight findByFlightNumber(String flightNumber);
}
