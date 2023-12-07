package com.safetynet.alert.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.DAO.AllData;
import com.safetynet.alert.model.DAO.FireStation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@Data
@Repository
public class FirestationRepository {

    @Autowired
    private AllDataRepository adr;

    public void postFirestation(String address, String stationNumber) throws IOException {
        AllData data = adr.getData();
        ArrayList<FireStation> fireStations = adr.getFireStations();
        FireStation newFirestation = new FireStation();
        newFirestation.setAddress(address);
        newFirestation.setStation(stationNumber) ;
        for(int i=0; i<fireStations.size(); i++) {
            if (newFirestation.getAddress().equals(fireStations.get(i).getAddress())&&newFirestation.getStation().equals(fireStations.get(i).getStation())){
                //TODO Add a logger
                return;  //we don't add if the Firestation is already in DB.
            }
        }
        fireStations.add(newFirestation);
        data.setFirestations(fireStations);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
    }

    public void putFirestation(String address, String stationNumber) throws IOException {
        AllData data = adr.getData();
        ArrayList<FireStation> fireStations = adr.getFireStations();
        for(int i=0; i<fireStations.size(); i++) {
            if (fireStations.get(i).getAddress().equals(address)){
                //TODO Add a logger
                fireStations.get(i).setStation(stationNumber);
                break;  //address is unique, no need to look for other stations
            }
        }
        data.setFirestations(fireStations);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
    }

    public void deleteStationByAddress(String address) throws IOException {
        AllData data = adr.getData();
        ArrayList<FireStation> fireStations = adr.getFireStations();
        for(int i=0; i<fireStations.size(); i++) {
            if (fireStations.get(i).getAddress().equals(address)){
                //TODO Add a logger
                fireStations.remove(i);
                break;  //address is unique, no need to look for other stations
            }
        }
        data.setFirestations(fireStations);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
    }

    public void deleteStationByNumber(String stationNumber) throws IOException {
        AllData data = adr.getData();
        ArrayList<FireStation> fireStations = adr.getFireStations();
        Iterator<FireStation> itr = fireStations.iterator();
        while (itr.hasNext()) {
            FireStation fireStation = itr.next();
            if (fireStation.getStation().equals(stationNumber)){
                itr.remove();
            }
        }
        data.setFirestations(fireStations);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
    }

}

