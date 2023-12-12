package com.safetynet.alert.service;

import com.safetynet.alert.model.DAO.Person;
import com.safetynet.alert.model.PersonInfoDTO.AllPersonInfo;
import com.safetynet.alert.model.PersonInfoDTO.PersonInfo;
import com.safetynet.alert.repository.AllDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class PersonInfoService {
    private static Logger logger = LoggerFactory.getLogger(PersonInfoService.class);
    @Autowired
    private AllDataRepository adr;
    @Autowired
    private AgeCalculatorUtil ageCalcul;
    @Autowired
    private AddressService addressService;

    private ArrayList<Person> getPersons() throws Exception {
        return adr.getPersons();
    }

    public AllPersonInfo getAllPersonInCityInfo(String firstName, String lastName) throws Exception {
        ArrayList<Person> persons = getPersons();
        ArrayList<PersonInfo> personsInfo = new ArrayList<>();
        for (int i=0; i< persons.size(); i++){
            if(persons.get(i).getFirstName().equals(firstName)&&persons.get(i).getLastName().equals(lastName)){
                PersonInfo p = personToPersonInfo(persons.get(i));
                personsInfo.add(p);
            }
        }
        AllPersonInfo allPersonInfo = new AllPersonInfo();
        allPersonInfo.setPersonsInfo(personsInfo);
        if (personsInfo.size()==0){
            logger.error("No one found with this name in DataBase");
        }
        else logger.info("All persons named "+firstName+" "+lastName+" info has been retrieved");
        return allPersonInfo;
    }

    public PersonInfo personToPersonInfo(Person person) throws Exception { //Converter
        PersonInfo p = new PersonInfo();
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setAddress(person.getAddress());
        p.setCity(person.getCity());
        p.setZip(person.getZip());
        p.setAge(ageCalcul.getAge(person));
        p.setMail(person.getEmail());
        p.setMedicalInfos(addressService.getPersonMedicalInfos(person));
        return p;
    }

}
