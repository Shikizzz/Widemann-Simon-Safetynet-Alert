package com.safetynet.alert.repository;
import com.safetynet.alert.CustomProperties;
import com.safetynet.alert.model.AllData;

import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import lombok.Data;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@Data
@Repository
public class AllDataRepository {

    private static Logger logger = LoggerFactory.getLogger(AllDataRepository.class);

    private File file;

    public AllDataRepository(CustomProperties props) throws FileNotFoundException {
        try {
            logger.debug(props.getData());
            this.file = new ClassPathResource(props.getData()).getFile(); //props.getData()
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    public AllData getData() throws FileNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        AllData allData = new AllData();
        try {
            allData = objectMapper.readValue(file, AllData.class);
        } catch (IOException e) {
            logger.error("Data file not found");
            throw new FileNotFoundException();
        }
        return allData;
    }
    public ArrayList<Person> getPersons() throws FileNotFoundException {
        AllData allData = getData();
        return allData.getPersons();
    }
    public ArrayList<FireStation> getFireStations() throws FileNotFoundException {
        AllData allData = getData();
        return allData.getFirestations();
    }
    public ArrayList<MedicalRecord> getMedicalRecords() throws FileNotFoundException {
        AllData allData = getData();
        return allData.getMedicalrecords();
    }
    public void modifyData(AllData data){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
