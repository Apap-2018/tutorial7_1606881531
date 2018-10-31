package com.apap.tutorial7.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDb;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightDb flightDb;
    
    @Override
    public void addFlight(FlightModel flight) {
        // TODO Auto-generated method stub
        flightDb.save(flight);
        
    }

    @Override
    public void deleteFlightById(long fm) {
        flightDb.deleteById(fm);
    }

}
