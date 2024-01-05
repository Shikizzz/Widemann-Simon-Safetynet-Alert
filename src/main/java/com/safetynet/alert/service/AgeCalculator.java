package com.safetynet.alert.service;

import com.safetynet.alert.model.DTO.FireStationDTO.PersonInStationZone;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.AllDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class AgeCalculator {
    private static Logger logger = LoggerFactory.getLogger(AgeCalculator.class);
    private AllDataRepository adr;
    public AgeCalculator(AllDataRepository adr) {
        this.adr = adr;
    }

    public int getAge(Person p) throws FileNotFoundException {
        String birthDateString = getBirthDate(p);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(birthDateString, pattern);
        LocalDate curDate = LocalDate.now();
        return Period.between(birthDate, curDate).getYears();

    }
    public String getBirthDate(Person p) throws FileNotFoundException {
        String firstName = p.getFirstName();
        String lastName = p.getLastName();
        ArrayList<MedicalRecord> records = adr.getMedicalRecords();
        for(int i=0; i<records.size(); i++){
            if(firstName.equals(records.get(i).getFirstName()) && lastName.equals(records.get(i).getLastName())){
                logger.debug("Birthdate get : " + records.get(i).getBirthdate());
                return records.get(i).getBirthdate();
            }
        }
        logger.error("Person's Medical Record not found, impossible to get Birthdate for Age calculation.");
        return null;
    }
}

