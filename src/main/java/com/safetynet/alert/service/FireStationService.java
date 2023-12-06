package com.safetynet.alert.service;

import com.safetynet.alert.model.DAO.FireStation;
import com.safetynet.alert.model.DAO.Person;
import com.safetynet.alert.model.FireStationDTO.AllPersonsInStationZone;
import com.safetynet.alert.model.FireStationDTO.PersonInStationZone;
import com.safetynet.alert.model.PhoneDTO.PhoneList;
import com.safetynet.alert.repository.AllDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class FireStationService {
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
        return result;
    }

    private ArrayList<Person> getPersonsInStationZone(String stationNumber) throws Exception {
        ArrayList<Person> persons = getPersons();
        ArrayList<FireStation> stationList = filterStationsByZone(stationNumber);
        ArrayList<Person> personsInZone = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            for (int j = 0; j < stationList.size(); j++) {
                if (persons.get(i).getAddress().equals(stationList.get(j).getAddress())) {
                    Person p = persons.get(i);
                    personsInZone.add(p);
                    break;
                }
            }
        }
        return personsInZone;
    }
    private ArrayList<FireStation> filterStationsByZone(String stationNumber) throws Exception {
        ArrayList<FireStation> stationList = new ArrayList<>();
        ArrayList<FireStation> fireStations = getFireStations();
        for (int i = 0; i < fireStations.size(); i++) {
            if (fireStations.get(i).getStation().equals(stationNumber)) {
                stationList.add(fireStations.get(i));
            }
        }
        return stationList;
    }


    public PhoneList getPhoneNumberInStationZone(String stationNumber) throws Exception {
        ArrayList<Person> persons = getPersonsInStationZone(stationNumber);
        ArrayList<String> phoneNumbers = new ArrayList<>();
        for(int i=0; i<persons.size(); i++){
            phoneNumbers.add(persons.get(i).getPhone());
        }
        PhoneList phoneList = new PhoneList();
        phoneList.setPhoneNumbers(phoneNumbers);
        return phoneList;
    }

}

