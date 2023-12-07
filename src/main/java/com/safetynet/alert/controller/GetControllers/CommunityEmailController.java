package com.safetynet.alert.controller.GetControllers;

import com.safetynet.alert.model.CommunityEmailDTO.CityEmails;
import com.safetynet.alert.service.CommunityEmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CommunityEmailController {

    private CommunityEmailService communityEmailService;

    @GetMapping("/communityEmail")
    public CityEmails getCityEmail(@RequestParam String city) throws Exception {
        return communityEmailService.getCityEmail(city);
    }
}


