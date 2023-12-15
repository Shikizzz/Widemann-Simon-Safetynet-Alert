package com.safetynet.alert.controller.get;

import com.safetynet.alert.CustomResponseEntity;
import com.safetynet.alert.model.DTO.CommunityEmailDTO.CityEmails;
import com.safetynet.alert.service.get.CommunityEmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
public class CommunityEmailController {

    private CustomResponseEntity customResponseEntity;

    private CommunityEmailService communityEmailService;

    @GetMapping("/communityEmail")
    public ResponseEntity<CityEmails> getCityEmail(@RequestParam String city) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(communityEmailService.getCityEmail(city));
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}


