package com.safetynet.alert.service.edit;

import com.safetynet.alert.model.AllData;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.AllDataRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@Data
@Repository
public class PersonEditer {

    private static Logger logger = LoggerFactory.getLogger(PersonEditer.class);
    @Autowired
    AllDataRepository adr;

    public void postPerson(Person person) throws FileNotFoundException {
        AllData data = adr.getData();
        ArrayList<Person> persons=  adr.getPersons();
        for(int i=0; i<persons.size(); i++) {
            if (person.getFirstName().equals(persons.get(i).getFirstName())&&person.getLastName().equals(persons.get(i).getLastName())){
                logger.error("This Person is already in DataBase, maybe you want to update it ?");
                return;  //we don't add if the person is already in DB.
            }
        }
        persons.add(person);
        data.setPersons(persons);
        adr.modifyData(data);
        logger.info("Person added to Database successfully");
    }

    public void putPerson(Person person) throws FileNotFoundException {
        AllData data = adr.getData();
        ArrayList<Person> persons=  adr.getPersons();
        for(int i=0; i<persons.size(); i++) {
            if (person.getFirstName().equals(persons.get(i).getFirstName())&&person.getLastName().equals(persons.get(i).getLastName())){
                persons.get(i).setAddress(person.getAddress());
                persons.get(i).setCity(person.getCity());
                persons.get(i).setZip(person.getZip());
                persons.get(i).setPhone(person.getPhone());
                persons.get(i).setEmail(person.getEmail());
                data.setPersons(persons);
                adr.modifyData(data);
                logger.info("Person updated in Database successfully");
                return;
            }
        }
        logger.error("Person not found in DataBase, maybe you want to add it ?");

    }

    public void deletePerson(String firstName, String lastName) throws FileNotFoundException {
        AllData data = adr.getData();
        ArrayList<Person> persons=  adr.getPersons();
        for(int i=0; i<persons.size(); i++) {
            if (firstName.equals(persons.get(i).getFirstName())&&lastName.equals(persons.get(i).getLastName())){
                persons.remove(i);
                data.setPersons(persons);
                adr.modifyData(data);
                logger.info("Person deleted from Database successfully");
                return;
            }
        }
        logger.error("No Person with this Name found in DataBase, nothing to delete");
    }

}
