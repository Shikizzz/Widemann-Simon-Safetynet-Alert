package com.safetynet.alert.model;

import lombok.Data;
import java.util.ArrayList;

@Data
public class AllData {

    private ArrayList<Person> persons;
    private ArrayList<FireStation> firestations;
    private ArrayList<MedicalRecord> medicalrecords;

    public AllData() {
    }

    public AllData(ArrayList<Person> persons, ArrayList<FireStation> firestations, ArrayList<MedicalRecord> medicalrecords) {
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalrecords;
    }
}
