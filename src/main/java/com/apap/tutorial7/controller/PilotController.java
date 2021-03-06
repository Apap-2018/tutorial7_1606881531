package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.rest.PilotDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/pilot")
public class PilotController {

    // REST API START //

    @Autowired
    RestTemplate rest;

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }

    @GetMapping(value = "/status/{licenseNumber}")
    public String getStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception {
        String path = Setting.pilotUrl + "/pilot?licenseNumber=" + licenseNumber;
        return rest.getForEntity(path, String.class).getBody();
    }

    @GetMapping(value = "/full/{licenseNumber}")
    public PilotDetail postStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception {
        String path = Setting.pilotUrl + "/pilot";
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        PilotDetail detail = rest.postForObject(path, pilot, PilotDetail.class);
        return detail;
    }


    // REST API END //

    @Autowired
    private PilotService pilotService;
    
    @PostMapping(value = "/add")
    public PilotModel addPilotSubmit(@RequestBody PilotModel pilot) {
        return pilotService.addPilot(pilot);
    }

    @GetMapping(value = "/view/{licenseNumber}")
    public PilotModel pilotView(@PathVariable("licenseNumber") String licenseNumber) {
        return pilotService.getPilotDetailByLicenseNumber(licenseNumber);
    }

    @DeleteMapping(value = "/delete")
    public String deletePilot(@RequestParam("pilotId") long pilotId) {
        PilotModel pilot = pilotService.getPilotDetailById(pilotId);
        pilotService.deletePilot(pilot);
        return "success";
    }

    @PutMapping(value = "/update/{pilotId}")
    public String updatePilotSubmit(@PathVariable("pilotId") long pilotId,
            @RequestParam("name") String name,
            @RequestParam("flyHour") int flyHour) {
        PilotModel pilot = pilotService.getPilotDetailById(pilotId);
        if (pilot == null) {
            return "couldn't find your pilot";
        }

        pilot.setName(name);
        pilot.setFlyHour(flyHour);
        pilotService.addPilot(pilot);
        return "update";
    }
}
