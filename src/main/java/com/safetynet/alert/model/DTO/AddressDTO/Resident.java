package com.safetynet.alert.model.DTO.AddressDTO;

import lombok.Data;
@Data
public class Resident {
    public String firstName;
    public String lastName;
    public String phone;
    public int age;
    public MedicalInfos medicalInfos;

    public Resident(String firstName, String lastName, String phone, int age, MedicalInfos medicalInfos) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medicalInfos = medicalInfos;
    }

    public Resident() {
    }
}
