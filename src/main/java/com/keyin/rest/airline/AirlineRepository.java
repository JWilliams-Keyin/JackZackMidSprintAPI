package com.keyin.rest.airline;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, Long>{
    public Airline findByAirlineName(String airlineName);
}
