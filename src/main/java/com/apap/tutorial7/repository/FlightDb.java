package com.apap.tutorial7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial7.model.FlightModel;

@Repository
public interface FlightDb extends JpaRepository<FlightModel, Long>{
    void deleteById(long id);
    FlightModel findFlightModelByFlightNumber(String flightNumber);
}
