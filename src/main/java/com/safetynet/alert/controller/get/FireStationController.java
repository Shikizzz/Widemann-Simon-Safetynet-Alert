package com.safetynet.alert.controller.get;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.service.get.FireStationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
public class FireStationController {
    private static Logger logger = LoggerFactory.getLogger(FireStationController.class);

    private CustomResponseEntity customResponseEntity;

    private FireStationService fireStationService;

    @GetMapping("/firestation")
    public ResponseEntity getAllPersonsInStationZone(@RequestParam String stationNumber) throws Exception {
        try {
            if (fireStationService.getAllPersonsInStationZone(stationNumber).getPersons().size() > 0) {
                logger.info("Endpoint GET/firestation used successfully, info retrieved");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(fireStationService.getAllPersonsInStationZone(stationNumber));
            } else {
                logger.info("Endpoint GET/firestation used successfully, but no info found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Noone found in this Firestation's jurisdiction. Station number may be wrong");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint GET/firestation used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}
