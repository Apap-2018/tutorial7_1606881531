package com.apap.tutorial7.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDb;

import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightDb flightDb;

    @Override
    public FlightModel getFlightById(long id) {
        return flightDb.getOne(id);
    }

    @Override
    public FlightModel addFlight(FlightModel flight) {
        // TODO Auto-generated method stub
        return flightDb.save(flight);
    }

    @Override
    public void deleteFlightById(long fm) {
        flightDb.deleteById(fm);
    }

    @Override
    public FlightModel getFlightByFlightNumber(String flightNumber) {
        return flightDb.findFlightModelByFlightNumber(flightNumber);
    }

    @Override
    public List<FlightModel> getAllFlight() {
        return flightDb.findAll();
    }

}
