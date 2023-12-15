package com.safetynet.alert.model.DTO.FireStationDTO;
import com.safetynet.alert.model.Person;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PersonInStationZone {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;

}
