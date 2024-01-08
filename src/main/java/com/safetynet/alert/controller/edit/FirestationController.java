package com.safetynet.alert.controller.edit;


import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.service.edit.FirestationEditer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
public class FirestationController {
    private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

    private CustomResponseEntity customResponseEntity;

    private FirestationEditer firestationEditer;

    @PostMapping("/firestation")
    public ResponseEntity<String> addFirestation(@RequestParam String address, @RequestParam String stationNumber) {
        try {
            if (firestationEditer.postFirestation(address, stationNumber)) {
                logger.info("Endpoint POST/firestation used successfully");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("FireStation added to Database successfully");
            } else {
                logger.info("Endpoint POST/firestation used, but the Firestation was already in DataBase ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Firestation already in DataBase");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint POST/firestation used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }

    @PutMapping("/firestation")
    public ResponseEntity<String> putFirestation(@RequestParam String address, @RequestParam String stationNumber) {
        try {
            if (firestationEditer.putFirestation(address, stationNumber)) {
                logger.info("Endpoint PUT/firestation used successfully");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("FireStation modified successfully");
            } else {
                logger.info("Endpoint PUT/firestation used, but the Firestation was not found in DataBase ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Firestation not found in DataBase");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint PUT/firestation used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }

    @DeleteMapping("/firestation/address")
    public ResponseEntity<String> deleteStationByAddress(@RequestParam String address) {
        try {
            if (firestationEditer.deleteStationByAddress(address)) {
                logger.info("Endpoint DELETE/firestation/address used successfully");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("FireStation deleted successfully");
            } else {
                logger.info("Endpoint DELETE/firestation/address used, but the Firestation was not found in DataBase ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Firestation not found in DataBase");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint DELETE/firestation/address used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }

    @DeleteMapping(value = "/firestation/stationNumber")
    public ResponseEntity<String> deleteStationByNumber(@RequestParam String stationNumber) {
        try {
            if (firestationEditer.deleteStationByNumber(stationNumber)) {
                logger.info("Endpoint DELETE/firestation/stationNumber used successfully");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("FireStation deleted successfully");
            } else {
                logger.info("Endpoint DELETE/firestation/stationNumber used, but the Firestation was not found in DataBase ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Firestation not found in DataBase");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint DELETE/firestation/stationNumber used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}
