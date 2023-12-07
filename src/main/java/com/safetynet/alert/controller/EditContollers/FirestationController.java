package com.safetynet.alert.controller.EditContollers;


import com.safetynet.alert.repository.FirestationRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RestController
public class FirestationController {

    private FirestationRepository firestationRepository;
    @PostMapping("/firestation")
    public void addFirestation(@RequestParam String address,@RequestParam String stationNumber) throws IOException {
        firestationRepository.postFirestation(address, stationNumber);
    }
    @PutMapping("/firestation")
    public void putFirestation(@RequestParam String address,@RequestParam String stationNumber) throws IOException {
        firestationRepository.putFirestation(address, stationNumber);
    }

    @DeleteMapping ("/firestation/address")
    public void deleteStationByAddress(@RequestParam String address) throws IOException {
        firestationRepository.deleteStationByAddress(address);
    }
    @DeleteMapping (value = "/firestation/stationNumber")
    public void deleteStationByNumber(@RequestParam String stationNumber) throws IOException {
        firestationRepository.deleteStationByNumber(stationNumber);
    }
}
