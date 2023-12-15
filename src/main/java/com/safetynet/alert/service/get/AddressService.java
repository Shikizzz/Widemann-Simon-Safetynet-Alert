package com.safetynet.alert.service.get;

import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.model.DTO.AddressDTO.AddressInfos;
import com.safetynet.alert.model.DTO.AddressDTO.Resident;
import com.safetynet.alert.model.DTO.AddressDTO.MedicalInfos;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.AgeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Component
public class AddressService {

    private static Logger logger = LoggerFactory.getLogger(AddressService.class);
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
    private ArrayList<MedicalRecord> getMedicalRecords() throws FileNotFoundException{
        return adr.getMedicalRecords();
    }

    public AddressInfos getAllAddressInfos(String address) throws FileNotFoundException { //
        AddressInfos infos = new AddressInfos();
        infos.setPeopleList(getAllPeopleList(address));
        infos.setFireStationNumber(getStationNumber(address));
        if(infos.getPeopleList().size()==0){
            logger.error("Address not found in DataBase" );
        }
        else{
            logger.info("Address found in DataBase, list of resident sent");
        }
        return infos;
    }
    public ArrayList<Resident> getAllPeopleList(String address) throws FileNotFoundException {
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
            }
        }
        return peopleInAddress;
    }
    public MedicalInfos getPersonMedicalInfos(Person person) throws FileNotFoundException {
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
    private String getStationNumber(String address) throws FileNotFoundException {
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
