package com.safetynet.alert.controller.get;

import com.safetynet.alert.CustomResponseEntity;
import com.safetynet.alert.model.DTO.FloodDTO.AllFloodStations;
import com.safetynet.alert.service.get.FloodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@AllArgsConstructor
@RestController
public class FloodController {

    private CustomResponseEntity customResponseEntity;

    private FloodService floodService;

    @GetMapping("/flood/stations")
    public ResponseEntity<AllFloodStations> getFloodInfo(@RequestParam ArrayList<String> stations) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(floodService.getFloodInfo(stations));
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}

