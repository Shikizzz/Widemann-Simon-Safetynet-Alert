package com.safetynet.alert.service;

import com.safetynet.alert.model.FireStationDTO.PersonInStationZone;
import com.safetynet.alert.model.DAO.MedicalRecord;
import com.safetynet.alert.model.DAO.Person;
import com.safetynet.alert.repository.AllDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static com.safetynet.alert.model.FireStationDTO.PersonInStationZone.personToPersonInStationZone;

@Component
public class AgeCalculatorUtil {
    private static Logger logger = LoggerFactory.getLogger(AgeCalculatorUtil.class);
    @Autowired
    private AllDataRepository adr;

    public ArrayList<MedicalRecord> getMedicalRecords() throws Exception{
        return adr.getMedicalRecords();
    }

    public int getAge(Person person) throws Exception {
        PersonInStationZone p = personToPersonInStationZone(person);
        return getAge(p);
    }

    public int getAge(PersonInStationZone p) throws Exception {
        String birthDateString = getBirthDate(p);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(birthDateString, pattern);
        LocalDate curDate = LocalDate.now();
        return Period.between(birthDate, curDate).getYears();

    }
    private String getBirthDate(PersonInStationZone p) throws Exception {
        String firstName = p.getFirstName();
        String lastName = p.getLastName();
        ArrayList<MedicalRecord> records = getMedicalRecords();
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

