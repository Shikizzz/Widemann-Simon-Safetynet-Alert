package com.safetynet.alert.controller.get;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.service.get.CommunityEmailService;
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
public class CommunityEmailController {
    private static Logger logger = LoggerFactory.getLogger(CommunityEmailController.class);

    private CustomResponseEntity customResponseEntity;

    private CommunityEmailService communityEmailService;

    @GetMapping("/communityEmail")
    public ResponseEntity getCityEmail(@RequestParam String city) throws Exception {
        try {
            if (communityEmailService.getCityEmail(city).getEMail().size() > 0) {
                logger.info("Endpoint GET/communityEmail used successfully, info retrieved");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(communityEmailService.getCityEmail(city));
            } else {
                logger.info("Endpoint GET/communityEmail used successfully, but no info found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Noone found in this city. Address may be wrong");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint GET/communityEmail used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}


