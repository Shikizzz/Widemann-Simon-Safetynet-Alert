package com.safetynet.alert.service.get;

import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.DTO.FloodDTO.AllFloodStations;
import com.safetynet.alert.model.DTO.FloodDTO.FloodStation;
import com.safetynet.alert.model.DTO.FloodDTO.Foyer;
import com.safetynet.alert.model.DTO.AddressDTO.Resident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Component
public class FloodService {
    private static Logger logger = LoggerFactory.getLogger(FloodService.class);
    private final AddressService addressService; //we will reUse fonctions already written in other services
    private final FireStationService fireStationService;
    public FloodService(AddressService addressService, FireStationService fireStationService) {
        this.addressService = addressService;
        this.fireStationService = fireStationService;
    }

    public AllFloodStations getFloodInfo(ArrayList<String> stationNumbers) throws FileNotFoundException { //No real logic here, assembling info in the allFloodStations DTO
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

    public ArrayList<Foyer> getFloodStationInfo (String stationNumber) throws FileNotFoundException {
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
