package com.safetynet.alert.service;

import com.safetynet.alert.model.DAO.FireStation;
import com.safetynet.alert.model.DAO.MedicalRecord;
import com.safetynet.alert.model.DAO.Person;
import com.safetynet.alert.model.addressDTO.AddressInfos;
import com.safetynet.alert.model.addressDTO.AddressPeople;
import com.safetynet.alert.model.addressDTO.MedicalInfos;
import com.safetynet.alert.repository.AllDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class AddressService {
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
    private ArrayList<MedicalRecord> getMedicalRecords() throws Exception{
        return adr.getMedicalRecords();
    }

    public AddressInfos getAllAddressInfos(String address) throws Exception {
        AddressInfos infos = new AddressInfos();
        infos.setPeopleList(getAllPeopleList(address));
        infos.setFireStationNumber(getStationNumber(address));
        return infos;
    }
    private ArrayList<AddressPeople> getAllPeopleList(String address) throws Exception {
        ArrayList<AddressPeople> peopleInAddress = new ArrayList<>();
        ArrayList<Person> persons = getPersons();
        for(int i=0; i< persons.size(); i++){
            if(persons.get(i).getAddress().equals(address)){
                AddressPeople people = new AddressPeople();
                people.setFirstName(persons.get(i).getFirstName());
                people.setLastName(persons.get(i).getLastName());
                people.setPhone(persons.get(i).getPhone());
                people.setAge(ageCalcul.getAge(persons.get(i)));
                people.setMedicalInfos(getPersonMedicalInfos(persons.get(i)));
                peopleInAddress.add(people);
            }
        }
        return peopleInAddress;
    }
    private MedicalInfos getPersonMedicalInfos(Person person) throws Exception {
        ArrayList<MedicalRecord> medicalList = getMedicalRecords();
        for(int i=0; i< medicalList.size(); i++){
            if(person.getFirstName().equals(medicalList.get(i).getFirstName())&&person.getLastName().equals(medicalList.get(i).getLastName())){
                MedicalInfos infos = new MedicalInfos();
                infos.setMedications(medicalList.get(i).getMedications());
                infos.setAllergies(medicalList.get(i).getAllergies());
                return infos;
            }
        }
        return new MedicalInfos(); //empty if medical record not found in data.json
    }
    private String getStationNumber(String address) throws Exception {
        ArrayList<FireStation> stations = getFireStations();
        for (int i=0; i<stations.size(); i++){
            if(stations.get(i).getAddress().equals(address)){
                return stations.get(i).getStation();
            }
        }
        return "Reference Firestation not found. Address may be wrong";
    }

}
