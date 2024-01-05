package com.safetynet.alert.service.get;

import com.safetynet.alert.model.Person;
import com.safetynet.alert.model.DTO.PersonInfoDTO.AllPersonInfo;
import com.safetynet.alert.model.DTO.PersonInfoDTO.PersonInfo;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.AgeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Component
public class PersonInfoService {
    private static Logger logger = LoggerFactory.getLogger(PersonInfoService.class);
    private final AllDataRepository adr;
    private final AgeCalculator ageCalcul;
    private final AddressService addressService;

    public PersonInfoService(AllDataRepository adr, AgeCalculator ageCalcul, AddressService addressService) {
        this.adr = adr;
        this.ageCalcul = ageCalcul;
        this.addressService = addressService;
    }

    public AllPersonInfo getAllPersonInCityInfo(String firstName, String lastName) throws FileNotFoundException {
        ArrayList<Person> persons = adr.getPersons();
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

    public PersonInfo personToPersonInfo(Person person) throws FileNotFoundException { //Converter
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
