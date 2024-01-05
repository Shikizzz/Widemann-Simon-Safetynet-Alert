package com.safetynet.alert.controller.get;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.model.DTO.AddressDTO.AddressInfos;
import com.safetynet.alert.service.get.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
public class AddressController {

    private CustomResponseEntity customResponseEntity;

    private AddressService addressService;

    @GetMapping("/fire")
    public ResponseEntity<AddressInfos> getAllAddressInfos(@RequestParam String address)  {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(addressService.getAllAddressInfos(address));
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}
