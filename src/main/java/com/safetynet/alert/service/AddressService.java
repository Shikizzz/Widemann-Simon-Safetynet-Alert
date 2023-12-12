package com.safetynet.alert.service;

import com.safetynet.alert.model.DAO.FireStation;
import com.safetynet.alert.model.DAO.MedicalRecord;
import com.safetynet.alert.model.DAO.Person;
import com.safetynet.alert.model.AddressDTO.AddressInfos;
import com.safetynet.alert.model.AddressDTO.Resident;
import com.safetynet.alert.model.AddressDTO.MedicalInfos;
import com.safetynet.alert.repository.AllDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class AddressService {

    private static Logger logger = LoggerFactory.getLogger(AddressService.class);
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

    public AddressInfos getAllAddressInfos(String address) throws Exception { //
        AddressInfos infos = new AddressInfos();
        infos.setPeopleList(getAllPeopleList(address));
        infos.setFireStationNumber(getStationNumber(address));
        return infos;
    }
    public ArrayList<Resident> getAllPeopleList(String address) throws Exception {
        ArrayList<Resident> peopleInAddress = new ArrayList<>();
        ArrayList<Person> persons = getPersons();
        for(int i=0; i< persons.size(); i++){
            if(persons.get(i).getAddress().equals(address)){
                Resident people = new Resident();
                people.setFirstName(persons.get(i).getFirstName());
                people.setLastName(persons.get(i).getLastName());
                people.setPhone(persons.get(i).getPhone());
                people.setAge(ageCalcul.getAge(persons.get(i)));
                people.setMedicalInfos(getPersonMedicalInfos(persons.get(i)));
                peopleInAddress.add(people);
                logger.debug("Address found in DataBase, list of resident sent" );
                return peopleInAddress;
            }
        }
        logger.error("Address not found in DataBase" );
        return peopleInAddress;
    }
    public MedicalInfos getPersonMedicalInfos(Person person) throws Exception {
        ArrayList<MedicalRecord> medicalList = getMedicalRecords();
        MedicalInfos infos = new MedicalInfos();
        for(int i=0; i< medicalList.size(); i++){
            if(person.getFirstName().equals(medicalList.get(i).getFirstName())&&person.getLastName().equals(medicalList.get(i).getLastName())){
                infos.setMedications(medicalList.get(i).getMedications());
                infos.setAllergies(medicalList.get(i).getAllergies());
                logger.debug("Medical Record found in DataBase, list of medications and allergies sent" );
                return infos; //LastName & FirstName is unique in MedicalInfo DB
            }
        }
        logger.error("Medical Record not found in DataBase" );
        return infos; //empty if medical record not found in data.json
    }
    private String getStationNumber(String address) throws Exception {
        ArrayList<FireStation> stations = getFireStations();
        for (int i=0; i<stations.size(); i++){
            if(stations.get(i).getAddress().equals(address)){
                logger.debug("FireStation found in DataBase" );
                return stations.get(i).getStation();
            }
        }
        logger.error("Reference Firestation not found. Address may be wrong" );
        return null;
    }

}
