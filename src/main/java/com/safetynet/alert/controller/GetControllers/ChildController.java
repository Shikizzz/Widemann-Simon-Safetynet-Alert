package com.safetynet.alert.controller.GetControllers;

import com.safetynet.alert.model.ChildDTO.ChildList;
import com.safetynet.alert.service.ChildService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ChildController {

    private ChildService childService;

    @GetMapping("/childAlert")
    public ChildList getChildrenInAddress(@RequestParam String address) throws Exception {
        return childService.getChildrenInAddress(address);
    }
}