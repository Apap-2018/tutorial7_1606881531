package com.apap.tutorial7.service;

import com.apap.tutorial7.model.FlightModel;

import java.util.List;

public interface FlightService {
    FlightModel getFlightById(long id);
    FlightModel addFlight(FlightModel flight);
    void deleteFlightById(long fm);

    FlightModel getFlightByFlightNumber(String flightNumber);

    List<FlightModel> getAllFlight();
}
