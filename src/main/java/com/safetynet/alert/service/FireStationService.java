package com.safetynet.alert.service;

import com.safetynet.alert.model.DAO.FireStation;
import com.safetynet.alert.model.DAO.Person;
import com.safetynet.alert.model.FireStationDTO.AllPersonsInStationZone;
import com.safetynet.alert.model.FireStationDTO.PersonInStationZone;
import com.safetynet.alert.model.PhoneDTO.PhoneList;
import com.safetynet.alert.repository.AllDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class FireStationService {
    private static Logger logger = LoggerFactory.getLogger(FireStationService.class);
    @Autowired
    private AllDataRepository adr;
    @Autowired
    private AgeCalculatorUtil ageCalcul;

    private ArrayList<Person> getPersons() throws Exception {
        return adr.getPersons();
    }
    private ArrayList<FireStation> getFireStations() throws Exception {
        return adr.getFireStations();
    }

    public AllPersonsInStationZone getAllPersonsInStationZone(String stationNumber) throws Exception {
        ArrayList<Person> p = getPersonsInStationZone(stationNumber);
        ArrayList<PersonInStationZone> persons = PersonInStationZone.personToPersonInStationZoneArray(p); //static method
        int adultCount = 0;
        int childrenCount = 0;
        for (int i = 0; i < persons.size(); i++) {
            if (ageCalcul.getAge(persons.get(i)) > 18) {
                adultCount++;
            } else {
                childrenCount++;
            }
        }
        AllPersonsInStationZone result = new AllPersonsInStationZone(persons, adultCount, childrenCount);
        if(p.size()>0) {
            logger.info("There are " + childrenCount + " children and " + adultCount + " adults at this Address. Their info has beeen retrieved");
        }
        return result;
    }

    private ArrayList<Person> getPersonsInStationZone(String stationNumber) throws Exception {
        ArrayList<Person> persons = getPersons();
        ArrayList<FireStation> stationList = filterStationsByZone(stationNumber);
        ArrayList<Person> personsInZone = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) { //for each person, we look if it is in Station zone
            for (int j = 0; j < stationList.size(); j++) {
                if (persons.get(i).getAddress().equals(stationList.get(j).getAddress())) {
                    Person p = persons.get(i);
                    personsInZone.add(p);
                    break; //no need to go further when we matched the person with his Firestation
                }
            }
        }
        if(personsInZone.size()==0){
            logger.error("No one found in the Firestation zone");
        }
        return personsInZone;
    }
    public ArrayList<FireStation> filterStationsByZone(String stationNumber) throws Exception {
        ArrayList<FireStation> stationList = new ArrayList<>();
        ArrayList<FireStation> fireStations = getFireStations();
        for (int i = 0; i < fireStations.size(); i++) {
            if (fireStations.get(i).getStation().equals(stationNumber)) {
                stationList.add(fireStations.get(i));
            }
        }
        if(stationList.size()==0){
            logger.error("No Firestation with number "+stationNumber+" found in database. Please retry with another number.");
        }
        return stationList;
    }


    public PhoneList getPhoneNumberInStationZone(String stationNumber) throws Exception {
        ArrayList<Person> persons = getPersonsInStationZone(stationNumber);
        ArrayList<String> phoneNumbers = new ArrayList<>();
        for(int i=0; i<persons.size(); i++){
            phoneNumbers.add(persons.get(i).getPhone());
        }
        ArrayList<String> phoneNumbersNoDouble = new ArrayList<>(new HashSet<>(phoneNumbers)); //removes doubles
        PhoneList phoneList = new PhoneList();
        phoneList.setPhoneNumbers(phoneNumbersNoDouble);
        if(phoneNumbersNoDouble.size()>0) {
            logger.info("The list of all Phone Numbers from people in Firestation" + stationNumber + "\'s juridiction have been retrieved");
        }
        return phoneList;
    }

}

