package com.keyin.rest.airport;

import com.keyin.rest.aircraft.Aircraft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Aircraft, Long> {
    public Aircraft findByAirportName(String airportName);

    public Aircraft findByAirportCode(String airportCode);
}
