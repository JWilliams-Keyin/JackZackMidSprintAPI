package com.keyin.rest.airport;

import com.keyin.rest.aircraft.Aircraft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
    public Airport findByAirportName(String airportName);

    public Airport findByAirportCode(String airportCode);
}
