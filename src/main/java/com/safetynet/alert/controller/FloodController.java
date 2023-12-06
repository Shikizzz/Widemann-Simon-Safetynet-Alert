package com.safetynet.alert.controller;

import com.safetynet.alert.model.FloodDTO.AllFloodStations;
import com.safetynet.alert.service.FloodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
public class FloodController {

    private FloodService floodService;

    @GetMapping("/flood/stations")
    public AllFloodStations getFloodInfo(@RequestParam ArrayList<String> stations) throws Exception {
        return floodService.getFloodInfo(stations);
    }
}

