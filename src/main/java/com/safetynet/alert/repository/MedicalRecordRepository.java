package com.safetynet.alert.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.DAO.AllData;
import com.safetynet.alert.model.DAO.MedicalRecord;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;

@Data
@Repository
public class MedicalRecordRepository {

    @Autowired
    AllDataRepository adr;
    public void postMedicalRecord(MedicalRecord medicalRecord) throws IOException {
        AllData data = adr.getData();
        ArrayList<MedicalRecord> medicalRecords =  adr.getMedicalRecords();
        for(int i=0; i<medicalRecords.size(); i++) {
            if (medicalRecord.getFirstName().equals(medicalRecords.get(i).getFirstName())&&medicalRecord.getLastName().equals(medicalRecords.get(i).getLastName())){
                //TODO Add a logger
                return;  //we don't add if the medicalRecord is already in DB.
            }
        }
        medicalRecords.add(medicalRecord);
        data.setMedicalrecords(medicalRecords);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
    }

    public void putMedicalRecord(MedicalRecord medicalRecord) throws IOException {
        AllData data = adr.getData();
        ArrayList<MedicalRecord> medicalRecords=  adr.getMedicalRecords();
        boolean found = false;
        for(int i=0; i<medicalRecords.size(); i++) {
            if (medicalRecord.getFirstName().equals(medicalRecords.get(i).getFirstName())&&medicalRecord.getLastName().equals(medicalRecords.get(i).getLastName())){
                found = true;
                medicalRecords.get(i).setBirthdate(medicalRecord.getBirthdate());
                medicalRecords.get(i).setMedications(medicalRecord.getMedications());
                medicalRecords.get(i).setAllergies(medicalRecord.getAllergies());
                break;
            }
        }
        if (!found){}//TODO Add a logger, medicalRecord not found in DB
        data.setMedicalrecords(medicalRecords);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
    }

    public void deleteMedicalRecord(String firstName, String lastName) throws IOException {
        AllData data = adr.getData();
        ArrayList<MedicalRecord> medicalRecords=  adr.getMedicalRecords();
        boolean found = false;
        for(int i=0; i<medicalRecords.size(); i++) {
            if (firstName.equals(medicalRecords.get(i).getFirstName())&&lastName.equals(medicalRecords.get(i).getLastName())){
                found = true;
                medicalRecords.remove(i);
                break;
            }
        }
        if (!found){}//TODO Add a logger, medicalRecord not found in DB
        data.setMedicalrecords(medicalRecords);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
    }
}
