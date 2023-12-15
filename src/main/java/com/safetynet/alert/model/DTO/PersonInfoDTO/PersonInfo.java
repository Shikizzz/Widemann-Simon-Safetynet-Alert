package com.safetynet.alert.model.DTO.PersonInfoDTO;

import com.safetynet.alert.model.DTO.AddressDTO.MedicalInfos;
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
