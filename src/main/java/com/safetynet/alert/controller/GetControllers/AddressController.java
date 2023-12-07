package com.safetynet.alert.controller.GetControllers;

import com.safetynet.alert.model.AddressDTO.AddressInfos;
import com.safetynet.alert.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AddressController {

    private AddressService addressService;

    @GetMapping("/fire")
    public AddressInfos getAllAddressInfos(@RequestParam String address) throws Exception {
        return addressService.getAllAddressInfos(address);
    }
}
