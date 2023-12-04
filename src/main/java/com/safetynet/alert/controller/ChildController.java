package com.safetynet.alert.controller;

import com.safetynet.alert.model.DTO.ChildInfo;
import com.safetynet.alert.service.ChildService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
public class ChildController {

    private ChildService childService;

    @GetMapping("/childAlert")
    public ArrayList<ChildInfo> getChildrenInAddress(@RequestParam String address) throws Exception {
        return childService.getChildrenInAddress(address);
    }
}