package com.safetynet.alert.model.addressDTO;

import lombok.Data;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;

@Data
public class AddressPeople {
    public String firstName;
    public String lastName;
    public String phone;
    public int age;
    public MedicalInfos medicalInfos;


}
