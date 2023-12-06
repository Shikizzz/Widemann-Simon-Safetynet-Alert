package com.safetynet.alert.controller;

import com.safetynet.alert.model.PhoneDTO.PhoneList;
import com.safetynet.alert.service.FireStationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
public class PhoneController {

    private FireStationService fireStationService;

    @GetMapping("/phoneAlert")
    public PhoneList getPhoneNumberInStationZone(@RequestParam String firestation) throws Exception {
        return fireStationService.getPhoneNumberInStationZone(firestation);
    }
}
