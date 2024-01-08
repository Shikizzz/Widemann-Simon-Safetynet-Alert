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
public class PhoneController {
    private static Logger logger = LoggerFactory.getLogger(AddressController.class);

    private CustomResponseEntity customResponseEntity;

    private FireStationService fireStationService;

    @GetMapping("/phoneAlert")
    public ResponseEntity getPhoneNumberInStationZone(@RequestParam String firestation) throws Exception {
        try {
            if (fireStationService.getPhoneNumberInStationZone(firestation).getPhoneNumbers().size() > 0) {
                logger.info("Endpoint GET/phoneAlert used successfully, info retrieved");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(fireStationService.getPhoneNumberInStationZone(firestation));
            } else {
                logger.info("Endpoint GET/phoneAlert used successfully, but no info found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Noone found in the Firestation's jurisdiction. Station number may be wrong");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint GET/phoneAlert used, but DataBase File found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}
