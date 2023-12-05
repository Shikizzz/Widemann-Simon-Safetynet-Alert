package com.safetynet.alert.model.DAO;

import lombok.Data;
import java.util.ArrayList;

@Data
public class AllData {

    private ArrayList<Person> persons = new ArrayList<Person>();
    private ArrayList<FireStation> firestations = new ArrayList<FireStation>();
    private ArrayList<MedicalRecord> medicalrecords = new ArrayList<MedicalRecord>();

}
