package com.safetynet.alert.controller.get;

import com.safetynet.alert.CustomResponseEntity;
import com.safetynet.alert.model.DTO.ChildDTO.ChildList;
import com.safetynet.alert.service.get.ChildService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
public class ChildController {

    private CustomResponseEntity customResponseEntity;

    private ChildService childService;

    @GetMapping("/childAlert")
    public ResponseEntity<ChildList> getChildrenInAddress(@RequestParam String address) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(childService.getChildrenInAddress(address));
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}