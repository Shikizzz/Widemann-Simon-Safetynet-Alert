package com.safetynet.alert.controller.get;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.service.get.AddressService;
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
public class AddressController {
    private static Logger logger = LoggerFactory.getLogger(AddressController.class);

    private CustomResponseEntity customResponseEntity;

    private AddressService addressService;

    @GetMapping("/fire")
    public ResponseEntity getAllAddressInfos(@RequestParam String address) {
        try {
            if (addressService.getAllAddressInfos(address).getPeopleList().size() > 0) {
                logger.info("Endpoint GET/fire used successfully, info retrieved");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(addressService.getAllAddressInfos(address));
            } else {
                logger.info("Endpoint GET/fire used successfully, but no info found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Noone found at this address. Address may be wrong");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint GET/fire used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}
