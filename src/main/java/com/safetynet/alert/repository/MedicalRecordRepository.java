package com.safetynet.alert.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.DAO.AllData;
import com.safetynet.alert.model.DAO.MedicalRecord;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;

@Data
@Repository
public class MedicalRecordRepository {

    private static Logger logger = LoggerFactory.getLogger(MedicalRecordRepository.class);
    @Autowired
    AllDataRepository adr;
    public void postMedicalRecord(MedicalRecord medicalRecord) throws IOException {
        AllData data = adr.getData();
        ArrayList<MedicalRecord> medicalRecords =  adr.getMedicalRecords();
        for(int i=0; i<medicalRecords.size(); i++) {
            if (medicalRecord.getFirstName().equals(medicalRecords.get(i).getFirstName())&&medicalRecord.getLastName().equals(medicalRecords.get(i).getLastName())){
                logger.error("This Medical Record is already in DataBase, maybe you want to update it ?");
                return;  //we don't add if the medicalRecord is already in DB.
            }
        }
        medicalRecords.add(medicalRecord);
        data.setMedicalrecords(medicalRecords);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
        logger.info("Medical Record added to Database successfully");
    }

    public void putMedicalRecord(MedicalRecord medicalRecord) throws IOException {
        AllData data = adr.getData();
        ArrayList<MedicalRecord> medicalRecords=  adr.getMedicalRecords();
        for(int i=0; i<medicalRecords.size(); i++) {
            if (medicalRecord.getFirstName().equals(medicalRecords.get(i).getFirstName())&&medicalRecord.getLastName().equals(medicalRecords.get(i).getLastName())){
                medicalRecords.get(i).setBirthdate(medicalRecord.getBirthdate());
                medicalRecords.get(i).setMedications(medicalRecord.getMedications());
                medicalRecords.get(i).setAllergies(medicalRecord.getAllergies());
                data.setMedicalrecords(medicalRecords);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
                logger.info("Medical Record updated in Database successfully");
                return;
            }
        }
        logger.error("Medical Record not found in DataBase, maybe you want to add it ?");
    }

    public void deleteMedicalRecord(String firstName, String lastName) throws IOException {
        AllData data = adr.getData();
        ArrayList<MedicalRecord> medicalRecords=  adr.getMedicalRecords();
        for(int i=0; i<medicalRecords.size(); i++) {
            if (firstName.equals(medicalRecords.get(i).getFirstName())&&lastName.equals(medicalRecords.get(i).getLastName())){
                medicalRecords.remove(i);
                data.setMedicalrecords(medicalRecords);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
                logger.info("Medical Record deleted from Database successfully");
                return;
            }
        }
        logger.error("No Medical Record with this Name found in DataBase, nothing to delete");

    }
}
