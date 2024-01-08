package com.safetynet.alert.controller.get;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.service.get.PersonInfoService;
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
public class PersonInfoController {
    private static Logger logger = LoggerFactory.getLogger(PersonInfoController.class);

    private CustomResponseEntity customResponseEntity;

    private PersonInfoService personInfoService;

    @GetMapping("/personInfo")
    public ResponseEntity getAllPersonInCityInfo(@RequestParam String firstName, @RequestParam String lastName) throws Exception {
        try {
            if (personInfoService.getAllPersonInCityInfo(firstName, lastName).getPersonsInfo().size() > 0) {
                logger.info("Endpoint GET/personInfo used successfully, info retrieved");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(personInfoService.getAllPersonInCityInfo(firstName, lastName));
            } else {
                logger.info("Endpoint GET/personInfo used successfully, but no info found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Noone found with this Name. Firstname or lastname may be wrong");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint GET/personInfo used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}



