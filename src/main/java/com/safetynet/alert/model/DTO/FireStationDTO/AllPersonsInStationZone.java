package com.safetynet.alert.model.DTO.FireStationDTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AllPersonsInStationZone {
    private ArrayList<PersonInStationZone> persons = new ArrayList<>();
    private int adultsNumber;
    private int childrenNumber;

    public AllPersonsInStationZone(ArrayList<PersonInStationZone> persons, int adultsNumber, int childrenNumber){
        this.persons = persons;
        this.adultsNumber = adultsNumber;
        this.childrenNumber = childrenNumber;
    }

}
