package com.safetynet.alert.service;

import com.safetynet.alert.model.DAO.FireStation;
import com.safetynet.alert.model.FloodDTO.AllFloodStations;
import com.safetynet.alert.model.FloodDTO.FloodStation;
import com.safetynet.alert.model.FloodDTO.Foyer;
import com.safetynet.alert.model.AddressDTO.Resident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class FloodService {
    private static Logger logger = LoggerFactory.getLogger(FloodService.class);
    @Autowired
    private AddressService addressService; //we will reUse fonctions already written in other services
    @Autowired
    private FireStationService fireStationService;

    public AllFloodStations getFloodInfo(ArrayList<String> stationNumbers) throws Exception { //No real logic here, assembling info in the allFloodStations DTO
        AllFloodStations allFloodStations = new AllFloodStations();
        ArrayList<FloodStation> floodStations = new ArrayList<FloodStation>();
        for(int i=0; i<stationNumbers.size(); i++){
            FloodStation floodStation = new FloodStation();
            floodStation.setStationNumber(stationNumbers.get(i));
            floodStation.setFoyers(getFloodStationInfo(stationNumbers.get(i))); //on cherche les foyers de chaque station
            floodStations.add(floodStation);
        }
        allFloodStations.setFloodStationsList(floodStations);
        logger.info("The list of all people in Firestations"+stationNumbers+"\'s juridictions have been retrieved, with their medical records");
        return allFloodStations;
    }

    public ArrayList<Foyer> getFloodStationInfo (String stationNumber) throws Exception {
        ArrayList<Foyer> foyers = new ArrayList<>();
        ArrayList<FireStation> station = fireStationService.filterStationsByZone(stationNumber);
        for(int i=0; i<station.size(); i++){
            String address = station.get(i).getAddress();
            ArrayList<Resident> residentList = addressService.getAllPeopleList(address);
            Foyer foyer = new Foyer();
            foyer.setAddress(address);
            foyer.setResidents(residentList);
            foyers.add(foyer);
        }
        return foyers;
    }
}
