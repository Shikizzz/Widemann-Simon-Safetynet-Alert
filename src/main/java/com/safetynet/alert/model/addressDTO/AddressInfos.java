package com.safetynet.alert.model.addressDTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AddressInfos {
    public ArrayList<AddressPeople> peopleList = new ArrayList<AddressPeople>();
    public String fireStationNumber;
}
