package com.safetynet.alert.controller.get;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.service.get.FloodService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(FloodController.class);

    private CustomResponseEntity customResponseEntity;

    private FloodService floodService;

    @GetMapping("/flood/stations")
    public ResponseEntity getFloodInfo(@RequestParam ArrayList<String> stations) throws Exception {
        try {
            if (floodService.getFloodInfo(stations).getFloodStationsList().size() > 0) {
                logger.info("Endpoint GET/flood/stations used successfully, info retrieved");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(floodService.getFloodInfo(stations));
            } else {
                logger.info("Endpoint GET/flood/stations used successfully, but no info found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Noone found in this Firestation's jurisdiction. Station numbers may be wrong");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint GET/flood/stations used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}

