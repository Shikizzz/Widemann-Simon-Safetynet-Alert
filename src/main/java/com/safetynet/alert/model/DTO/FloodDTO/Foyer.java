package com.safetynet.alert.model.DTO.FloodDTO;

import com.safetynet.alert.model.DTO.AddressDTO.Resident;
import lombok.Data;

import java.util.ArrayList;
@Data
public class Foyer {
    private String address;
    private ArrayList<Resident> residents = new ArrayList<Resident>();
}
