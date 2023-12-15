package com.safetynet.alert.model.DTO.AddressDTO;

import lombok.Data;
@Data
public class Resident {
    public String firstName;
    public String lastName;
    public String phone;
    public int age;
    public MedicalInfos medicalInfos;


}
