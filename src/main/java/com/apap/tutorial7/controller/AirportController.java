package com.apap.tutorial7.controller;

import com.apap.tutorial7.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    RestTemplate rest;

    @Bean
    public RestTemplate rest2() {
        return new RestTemplate();
    }

    @GetMapping(value = "/{indonesianCity}")
    public String getAirportInACity(@PathVariable("indonesianCity") String city) {
        String url = Setting.airportUrl + city;
        return rest.getForEntity(url, String.class).getBody();
    }
}
