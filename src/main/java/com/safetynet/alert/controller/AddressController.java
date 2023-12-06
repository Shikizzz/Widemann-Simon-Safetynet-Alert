package com.safetynet.alert.controller;

import com.safetynet.alert.model.ChildDTO.ChildList;
import com.safetynet.alert.model.addressDTO.AddressInfos;
import com.safetynet.alert.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AddressController {

    private AddressService addressServiceService;

    @GetMapping("/fire")
    public AddressInfos getChildrenInAddress(@RequestParam String address) throws Exception {
        return addressServiceService.getAllAddressInfos(address);
    }
}
