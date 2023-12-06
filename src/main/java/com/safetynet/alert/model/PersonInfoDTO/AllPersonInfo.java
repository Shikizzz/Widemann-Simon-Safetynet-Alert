package com.safetynet.alert.model.PersonInfoDTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AllPersonInfo {
    ArrayList <PersonInfo> personsInfo = new ArrayList<PersonInfo>();
}
