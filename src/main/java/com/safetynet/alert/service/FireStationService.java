package com.safetynet.alert.service;

import com.safetynet.alert.model.*;
import com.safetynet.alert.model.DTO.AllPersonsInStationZone;
import com.safetynet.alert.model.DTO.PersonInStationZone;
import com.safetynet.alert.repository.AllDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;


@Component
public class FireStationService {
    @Autowired
    private AllDataRepository adr;

    public AllData getData() throws Exception{
        return adr.getData();
    }
    public ArrayList<Person> getPersons() throws Exception{
        return adr.getPersons();
    }
    public ArrayList<FireStation> getFireStations() throws Exception{
        return adr.getFireStations();
    }
    public ArrayList<MedicalRecord> getMedicalRecords() throws Exception{
        return adr.getMedicalRecords();
    }

    public AllPersonsInStationZone getAllPersonsInStationZone(String stationNumber) throws Exception {
        ArrayList<PersonInStationZone> persons = getPersonsInStationZone(stationNumber);
        int adultCount = 0;
        int childrenCount = 0;
        for(int i=0; i<persons.size(); i++){
            if(getAge(persons.get(i))>18){
                adultCount++;
            }
            else{
                childrenCount++;
            }
        }
        AllPersonsInStationZone result = new AllPersonsInStationZone(persons, adultCount, childrenCount);
        return result;
    }
    public ArrayList<PersonInStationZone> getPersonsInStationZone(String stationNumber) throws Exception {
        ArrayList<Person> persons = getPersons();
        ArrayList<FireStation> stationList= filterStationsByZone(stationNumber);
        ArrayList<PersonInStationZone> personsInZone = new ArrayList<>();
        for(int i=0; i<persons.size(); i++){
            for(int j=0; j< stationList.size(); j++) {
                if (persons.get(i).getAddress().equals(stationList.get(j).getAddress())){
                    PersonInStationZone p = PersonInStationZone.personToPersonInStationZone(persons.get(i)); //call to a Static Method. OK ?
                    personsInZone.add(p);
                    break;
                }
            }
        }
        return personsInZone;
    }
    public ArrayList<FireStation> filterStationsByZone(String stationNumber) throws Exception {
        ArrayList<FireStation> stationList = new ArrayList<>();
        ArrayList<FireStation> fireStations = getFireStations();
        for(int i=0; i<fireStations.size(); i++){
            if(fireStations.get(i).getStation().equals(stationNumber)){
                stationList.add(fireStations.get(i));
            }
        }
        return stationList;
    }
    public int getAge(PersonInStationZone p) throws Exception {
        String birthDateString = getBirthDate(p);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(birthDateString, pattern);
        LocalDate curDate = LocalDate.now();
        return Period.between(birthDate, curDate).getYears();

    }
    public String getBirthDate(PersonInStationZone p) throws Exception {
        String firstName = p.getFirstName();
        String lastName = p.getLastName();
        ArrayList<MedicalRecord> records = getMedicalRecords();
        for(int i=0; i<records.size(); i++){
            if(firstName.equals(records.get(i).getFirstName()) && lastName.equals(records.get(i).getLastName())){
                return records.get(i).getBirthdate();
            }
        }
        return null; //gérer l'exception
    }
}
