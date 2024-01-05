package com.safetynet.alert.controller.get;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.model.DTO.PersonInfoDTO.AllPersonInfo;
import com.safetynet.alert.service.get.PersonInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
public class PersonInfoController {

    private CustomResponseEntity customResponseEntity;

    private PersonInfoService personInfoService;

    @GetMapping("/personInfo")
    public ResponseEntity<AllPersonInfo> getAllPersonInCityInfo(@RequestParam String firstName,@RequestParam String lastName) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personInfoService.getAllPersonInCityInfo(firstName, lastName));
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}



