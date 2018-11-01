package com.apap.tutorial7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    PilotService pilotService;

    @PostMapping(value = "/add")
    public FlightModel addFlight(@RequestBody FlightModel flightModel) {
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(flightModel.getPilot().getLicenseNumber());
        flightModel.setPilot(pilot);
        return flightService.addFlight(flightModel);
    }

    @PutMapping(value = "/update/{flightId}")
    public String updateFlight( @PathVariable("flightId") long flightId,
            @RequestParam("destination") Optional<String> destination,
            @RequestParam("origin") Optional<String> origin,
            @RequestParam("time") Optional<Date> time) {

        FlightModel flight = flightService.getFlightById(flightId);
        if (destination.isPresent()) {
            flight.setDestination(destination.get());
            System.out.println(destination.get());
        }
        if (origin.isPresent()) {
            flight.setOrigin(origin.get());
            System.out.println(origin.get());
        }
        if (time.isPresent()) {
            flight.setTime(time.get());
            System.out.println(time.get());
        }
        flightService.addFlight(flight);

        return "flight update success";
    }

    @GetMapping(value = "/view/{flightNumber}")
    public FlightModel getFlight(@PathVariable("flightNumber") String flightNumber) {
        return flightService.getFlightByFlightNumber(flightNumber);
    }

    @GetMapping(value = "/all")
    public List<FlightModel> getAllFlight() {
        return flightService.getAllFlight();
    }

    @DeleteMapping(value = "/delete/{flightId}")
    public String deleteFlight(@PathVariable("flightId") long flightId) {
        flightService.deleteFlightById(flightId);
        return "flight has been deleted";
    }
}
