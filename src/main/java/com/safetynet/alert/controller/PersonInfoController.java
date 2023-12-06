package com.safetynet.alert.controller;

import com.safetynet.alert.model.PersonInfoDTO.AllPersonInfo;
import com.safetynet.alert.service.PersonInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PersonInfoController {

    private PersonInfoService personInfoService;

    @GetMapping("/personInfo")
    public AllPersonInfo getAllPersonInCityInfo(@RequestParam String firstName,@RequestParam String lastName) throws Exception {
        return personInfoService.getAllPersonInCityInfo(firstName, lastName);
    }
}



