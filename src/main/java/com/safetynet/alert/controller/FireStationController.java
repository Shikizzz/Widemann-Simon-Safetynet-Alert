package com.safetynet.alert.controller;

import com.safetynet.alert.model.FireStationDTO.AllPersonsInStationZone;
import com.safetynet.alert.service.FireStationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
public class FireStationController {

    private FireStationService fireStationService;

    @GetMapping("/firestation")
    public AllPersonsInStationZone getAllPersonsInStationZone( @RequestParam String stationNumber) throws Exception {
        return fireStationService.getAllPersonsInStationZone(stationNumber);
    }



    /*
    public String allDataToString() throws Exception {
        return testService.getData().toString();
    }
    */
}
