package com.safetynet.alert.model.PersonInfoDTO;

import com.safetynet.alert.model.AddressDTO.MedicalInfos;
import lombok.Data;

@Data
public class PersonInfo {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private int age;
    private String mail;
    private MedicalInfos medicalInfos;

}
