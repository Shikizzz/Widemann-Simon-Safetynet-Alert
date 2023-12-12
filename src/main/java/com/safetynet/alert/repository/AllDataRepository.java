package com.safetynet.alert.repository;
import com.safetynet.alert.model.DAO.AllData;

import com.safetynet.alert.model.DAO.FireStation;
import com.safetynet.alert.model.DAO.MedicalRecord;
import com.safetynet.alert.model.DAO.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import lombok.Data;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;

@Data
@Repository
public class AllDataRepository {

    private static Logger logger = LoggerFactory.getLogger(AllDataRepository.class);

    private File file = new File("C:\\Users\\widem\\Desktop\\OpenClassrooms\\P5\\alert\\src\\main\\resources\\dataTest.json");
    /*Resource resource = new ClassPathResource("../../../../../resources/data.json");
    File file;
    {
        try {
            file = resource.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public AllData getData(){
        ObjectMapper objectMapper = new ObjectMapper();
        AllData allData = null;
        try {
            allData = objectMapper.readValue(file, AllData.class);
        } catch (IOException e) {
            logger.error("Data file not found");
            throw new RuntimeException(e);
        }
        return allData;
    }
    public ArrayList<Person> getPersons(){
        AllData allData = getData();
        return allData.getPersons();
    }
    public ArrayList<FireStation> getFireStations(){
        AllData allData = getData();
        return allData.getFirestations();
    }
    public ArrayList<MedicalRecord> getMedicalRecords(){
        AllData allData = getData();
        return allData.getMedicalrecords();
    }

}
