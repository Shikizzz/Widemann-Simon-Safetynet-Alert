package com.safetynet.alert.model.DTO.AddressDTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AddressInfos {
    public ArrayList<Resident> peopleList = new ArrayList<Resident>();
    public String fireStationNumber;
}
