package com.safetynet.alert.service.get;

import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.model.DTO.FireStationDTO.AllPersonsInStationZone;
import com.safetynet.alert.model.DTO.FireStationDTO.PersonInStationZone;
import com.safetynet.alert.model.DTO.PhoneDTO.PhoneList;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.AgeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class FireStationService {
    private static Logger logger = LoggerFactory.getLogger(FireStationService.class);
    @Autowired
    private AllDataRepository adr;
    @Autowired
    private AgeCalculator ageCalcul;

    private ArrayList<Person> getPersons() throws FileNotFoundException {
        return adr.getPersons();
    }
    private ArrayList<FireStation> getFireStations() throws FileNotFoundException {
        return adr.getFireStations();
    }

    public AllPersonsInStationZone getAllPersonsInStationZone(String stationNumber) throws FileNotFoundException {
        ArrayList<Person> p = getPersonsInStationZone(stationNumber);
        int adultCount = 0;
        int childrenCount = 0;
        for (int i = 0; i < p.size(); i++) {
            if (ageCalcul.getAge(p.get(i)) > 18) {
                adultCount++;
            } else {
                childrenCount++;
            }
        }
        ArrayList<PersonInStationZone> persons = personToPersonInStationZoneArray(p);
        AllPersonsInStationZone result = new AllPersonsInStationZone(persons, adultCount, childrenCount);
        if(p.size()>0) {
            logger.info("There are " + childrenCount + " children and " + adultCount + " adults at this Address. Their info has beeen retrieved");
        }
        return result;
    }

    private ArrayList<Person> getPersonsInStationZone(String stationNumber) throws FileNotFoundException {
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
    public ArrayList<FireStation> filterStationsByZone(String stationNumber) throws FileNotFoundException {
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

    public static PersonInStationZone personToPersonInStationZone(Person person){ //transformer
        PersonInStationZone p = new PersonInStationZone();
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setAddress(person.getAddress());
        p.setCity(person.getCity());
        p.setZip(person.getZip());
        p.setPhone(person.getPhone());
        return p;
    }

    public static ArrayList<PersonInStationZone> personToPersonInStationZoneArray(ArrayList<Person> persons) { //transformer
        ArrayList<PersonInStationZone> p = new ArrayList<>();
        for (int i=0; i< persons.size(); i++){
            p.add(personToPersonInStationZone(persons.get(i)));
        }
        return p;
    }


    public PhoneList getPhoneNumberInStationZone(String stationNumber) throws FileNotFoundException {
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

