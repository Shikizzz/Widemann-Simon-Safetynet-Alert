package com.safetynet.alert.service.edit;

import com.safetynet.alert.model.AllData;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.repository.AllDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Component
public class MedicalRecordEditer {

    private static Logger logger = LoggerFactory.getLogger(MedicalRecordEditer.class);
    private final AllDataRepository adr;

    public MedicalRecordEditer(AllDataRepository adr) {
        this.adr = adr;
    }

    public Boolean postMedicalRecord(MedicalRecord medicalRecord) throws FileNotFoundException {
        AllData data = adr.getData();
        ArrayList<MedicalRecord> medicalRecords = data.getMedicalrecords();
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecord.getFirstName().equals(medicalRecords.get(i).getFirstName()) && medicalRecord.getLastName().equals(medicalRecords.get(i).getLastName())) {
                logger.error("This Medical Record is already in DataBase, maybe you want to update it ?");
                return false;  //we don't add if the medicalRecord is already in DB.
            }
        }
        medicalRecords.add(medicalRecord);
        data.setMedicalrecords(medicalRecords);
        adr.modifyData(data);
        logger.info("Medical Record added to Database successfully");
        return true;
    }

    public Boolean putMedicalRecord(MedicalRecord medicalRecord) throws FileNotFoundException {
        AllData data = adr.getData();
        ArrayList<MedicalRecord> medicalRecords = data.getMedicalrecords();
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecord.getFirstName().equals(medicalRecords.get(i).getFirstName()) && medicalRecord.getLastName().equals(medicalRecords.get(i).getLastName())) {
                medicalRecords.get(i).setBirthdate(medicalRecord.getBirthdate());
                medicalRecords.get(i).setMedications(medicalRecord.getMedications());
                medicalRecords.get(i).setAllergies(medicalRecord.getAllergies());
                data.setMedicalrecords(medicalRecords);
                adr.modifyData(data);
                logger.info("Medical Record updated in Database successfully");
                return true;
            }
        }
        logger.error("Medical Record not found in DataBase, maybe you want to add it ?");
        return false;
    }

    public Boolean deleteMedicalRecord(String firstName, String lastName) throws FileNotFoundException {
        AllData data = adr.getData();
        ArrayList<MedicalRecord> medicalRecords = data.getMedicalrecords();
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (firstName.equals(medicalRecords.get(i).getFirstName()) && lastName.equals(medicalRecords.get(i).getLastName())) {
                medicalRecords.remove(i);
                data.setMedicalrecords(medicalRecords);
                adr.modifyData(data);
                logger.info("Medical Record deleted from Database successfully");
                return true;
            }
        }
        logger.error("No Medical Record with this Name found in DataBase, nothing to delete");
        return false;
    }
}
