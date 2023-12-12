package com.safetynet.alert.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.DAO.AllData;
import com.safetynet.alert.model.DAO.FireStation;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@Data
@Repository
public class FirestationRepository {

    private static Logger logger = LoggerFactory.getLogger(FirestationRepository.class);
    @Autowired
    private AllDataRepository adr;

    public void postFirestation(String address, String stationNumber) throws IOException {
        AllData data = adr.getData();
        ArrayList<FireStation> fireStations = adr.getFireStations();
        FireStation newFirestation = new FireStation();
        newFirestation.setAddress(address);
        newFirestation.setStation(stationNumber) ;
        for(int i=0; i<fireStations.size(); i++) {
            if (newFirestation.getAddress().equals(fireStations.get(i).getAddress())){
                logger.error("This Firestation is already in DataBase, maybe you want to update it ?");
                return;  //we don't add if the Firestation is already in DB.
            }
        }
        fireStations.add(newFirestation);
        data.setFirestations(fireStations);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
        logger.info("Firestation added to Database successfully");
    }

    public void putFirestation(String address, String stationNumber) throws IOException {
        AllData data = adr.getData();
        ArrayList<FireStation> fireStations = adr.getFireStations();
        for(int i=0; i<fireStations.size(); i++) {
            if (fireStations.get(i).getAddress().equals(address)){
                fireStations.get(i).setStation(stationNumber);
                data.setFirestations(fireStations);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
                logger.info("Firestation updated in Database successfully");
                return;  //address is unique, no need to look for other stations
            }
        }
        logger.error("Firestation not found in DataBase, maybe you want to add it ?");
    }

    public void deleteStationByAddress(String address) throws IOException {
        AllData data = adr.getData();
        ArrayList<FireStation> fireStations = adr.getFireStations();
        for(int i=0; i<fireStations.size(); i++) {
            if (fireStations.get(i).getAddress().equals(address)){
                fireStations.remove(i);
                data.setFirestations(fireStations);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
                logger.info("Firestation deleted from Database successfully");
                return;  //address is unique, no need to look for other stations
            }
        }
        logger.error("Firestation not found in DataBase, nothing to delete");
    }

    public void deleteStationByNumber(String stationNumber) throws IOException {
        AllData data = adr.getData();
        ArrayList<FireStation> fireStations = adr.getFireStations();
        Iterator<FireStation> itr = fireStations.iterator();
        boolean foundOne = false;
        while (itr.hasNext()) {
            FireStation fireStation = itr.next();
            if (fireStation.getStation().equals(stationNumber)){
                itr.remove();
                foundOne = true;
            }
        }
        data.setFirestations(fireStations);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
        if(foundOne){
            logger.info("Firestations deleted from Database successfully");
        }
        else logger.error("No Firestation with this number found in DataBase, nothing to delete");
    }

}

