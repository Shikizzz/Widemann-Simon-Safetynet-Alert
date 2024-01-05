package com.safetynet.alert.IntegrationTests;

import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.AllDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Component
public class JsonVerifier {

    private final AllDataRepository adr;

    public JsonVerifier(AllDataRepository adr) {
        this.adr = adr;
    }

    public Boolean fireStationFound(String address, String stationNumber) throws FileNotFoundException {
        ArrayList<FireStation> fireStations = adr.getFireStations();
        for (int i=0; i< fireStations.size(); i++){
            if(fireStations.get(i).getAddress().equals(address)&&fireStations.get(i).getStation().equals(stationNumber)){
                return true;
            }
        }
        return false;
    }
    public Boolean personFound(String firstName, String lastName) throws FileNotFoundException {
        ArrayList<Person> persons = adr.getPersons();
        for (int i=0; i< persons.size(); i++){
            if(persons.get(i).getFirstName().equals(firstName)&&persons.get(i).getLastName().equals(lastName)){
                return true;
            }
        }
        return false;
    }
    public Boolean personFound(Person person) throws FileNotFoundException {
        ArrayList<Person> persons = adr.getPersons();
        for (int i=0; i< persons.size(); i++){
            if(persons.get(i).equals(person)){
                return true;
            }
        }
        return false;
    }
    public Boolean medicalRecordFound(String firstName, String lastName) throws FileNotFoundException {
        ArrayList<MedicalRecord> medicalRecords = adr.getMedicalRecords();
        for (int i=0; i< medicalRecords.size(); i++){
            if(medicalRecords.get(i).getFirstName().equals(firstName)&&medicalRecords.get(i).getLastName().equals(lastName)){
                return true;
            }
        }
        return false;
    }
    public Boolean medicalRecordFound(MedicalRecord medicalRecord) throws FileNotFoundException {
        ArrayList<MedicalRecord> medicalRecords = adr.getMedicalRecords();
        for (int i=0; i< medicalRecords.size(); i++){
            if(medicalRecords.get(i).equals(medicalRecord)){
                return true;
            }
        }
        return false;
    }
}
