package com.safetynet.alert.repository;
import com.safetynet.alert.model.AllData;

import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import lombok.Data;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;

@Data
@Repository
public class AllDataRepository {

    private File file = new File("C:\\Users\\widem\\Desktop\\OpenClassrooms\\P5\\alert\\src\\main\\resources\\data.json");
    /*Resource resource = new ClassPathResource("../../../../../resources/data.json");
    File file;
    {
        try {
            file = resource.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public AllData getData() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        AllData allData = objectMapper.readValue(file, AllData.class);
        return allData;
    }
    public ArrayList<Person> getPersons() throws IOException{
        AllData allData = getData();
        return allData.getPersons();
    }
    public ArrayList<FireStation> getFireStations() throws IOException{
        AllData allData = getData();
        return allData.getFirestations();
    }
    public ArrayList<MedicalRecord> getMedicalRecords() throws IOException{
        AllData allData = getData();
        return allData.getMedicalrecords();
    }
}
