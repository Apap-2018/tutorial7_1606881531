package com.apap.tutorial7.service;

import com.apap.tutorial7.model.FlightModel;

public interface FlightService {
    void addFlight(FlightModel flight);
    void deleteFlightById(long fm);
}
