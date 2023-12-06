package com.safetynet.alert.model.FloodDTO;

import com.safetynet.alert.model.AddressDTO.Resident;
import lombok.Data;

import java.util.ArrayList;
@Data
public class Foyer {
    private String address;
    private ArrayList<Resident> residents = new ArrayList<Resident>();
}
