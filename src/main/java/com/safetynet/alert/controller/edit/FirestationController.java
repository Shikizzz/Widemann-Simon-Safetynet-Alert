package com.safetynet.alert.controller.edit;


import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.service.edit.FirestationEditer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
public class FirestationController {

    private CustomResponseEntity customResponseEntity;

    private FirestationEditer firestationEditer;
    @PostMapping("/firestation")
    public ResponseEntity<String> addFirestation(@RequestParam String address, @RequestParam String stationNumber){
        try {
            firestationEditer.postFirestation(address, stationNumber);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("FireStation added to Database successfully");
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundEditResponseEntity();
        }
    }
    @PutMapping("/firestation")
    public ResponseEntity<String> putFirestation(@RequestParam String address,@RequestParam String stationNumber){
        try {
            firestationEditer.putFirestation(address, stationNumber);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("FireStation modified successfully");
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundEditResponseEntity();
        }
    }

    @DeleteMapping ("/firestation/address")
    public ResponseEntity<String> deleteStationByAddress(@RequestParam String address){
        try {
            firestationEditer.deleteStationByAddress(address);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("FireStation deleted successfully");
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundEditResponseEntity();
        }
    }
    @DeleteMapping (value = "/firestation/stationNumber")
    public ResponseEntity<String> deleteStationByNumber(@RequestParam String stationNumber){
        try {
            firestationEditer.deleteStationByNumber(stationNumber);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("FireStation deleted successfully");
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundEditResponseEntity();
        }

    }
}
