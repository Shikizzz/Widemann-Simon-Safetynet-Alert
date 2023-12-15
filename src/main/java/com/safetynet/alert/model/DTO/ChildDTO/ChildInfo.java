package com.safetynet.alert.model.DTO.ChildDTO;

import com.safetynet.alert.model.DTO.FireStationDTO.PeopleIdentity;
import lombok.Data;

import java.util.ArrayList;
@Data
public class ChildInfo {
    private String firstName;
    private String lastName;
    private int age;
    private ArrayList<PeopleIdentity> others = new ArrayList<>();
}
