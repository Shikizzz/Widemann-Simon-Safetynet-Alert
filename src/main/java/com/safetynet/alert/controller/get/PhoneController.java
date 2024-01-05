package com.safetynet.alert.controller.get;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.model.DTO.PhoneDTO.PhoneList;
import com.safetynet.alert.service.get.FireStationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
public class PhoneController {

    private CustomResponseEntity customResponseEntity;

    private FireStationService fireStationService;

    @GetMapping("/phoneAlert")
    public ResponseEntity<PhoneList> getPhoneNumberInStationZone(@RequestParam String firestation) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(fireStationService.getPhoneNumberInStationZone(firestation));
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}
