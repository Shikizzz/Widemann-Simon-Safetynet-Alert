package com.safetynet.alert.service.edit;

import com.safetynet.alert.model.AllData;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.AllDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Component
public class PersonEditer {

    private static Logger logger = LoggerFactory.getLogger(PersonEditer.class);
    private final AllDataRepository adr;

    public PersonEditer(AllDataRepository adr) {
        this.adr = adr;
    }

    public Boolean postPerson(Person person) throws FileNotFoundException {
        AllData data = adr.getData();
        ArrayList<Person> persons = data.getPersons();
        for (int i = 0; i < persons.size(); i++) {
            if (person.getFirstName().equals(persons.get(i).getFirstName()) && person.getLastName().equals(persons.get(i).getLastName())) {
                logger.error("This Person is already in DataBase, maybe you want to update it ?");
                return false;  //we don't add if the person is already in DB.
            }
        }
        persons.add(person);
        data.setPersons(persons);
        adr.modifyData(data);
        logger.info("Person added to Database successfully");
        return true;
    }

    public Boolean putPerson(Person person) throws FileNotFoundException {
        AllData data = adr.getData();
        ArrayList<Person> persons = data.getPersons();
        for (int i = 0; i < persons.size(); i++) {
            if (person.getFirstName().equals(persons.get(i).getFirstName()) && person.getLastName().equals(persons.get(i).getLastName())) {
                persons.get(i).setAddress(person.getAddress());
                persons.get(i).setCity(person.getCity());
                persons.get(i).setZip(person.getZip());
                persons.get(i).setPhone(person.getPhone());
                persons.get(i).setEmail(person.getEmail());
                data.setPersons(persons);
                adr.modifyData(data);
                logger.info("Person updated in Database successfully");
                return true;
            }
        }
        logger.error("Person not found in DataBase, maybe you want to add it ?");
        return false;
    }

    public Boolean deletePerson(String firstName, String lastName) throws FileNotFoundException {
        AllData data = adr.getData();
        ArrayList<Person> persons = data.getPersons();
        for (int i = 0; i < persons.size(); i++) {
            if (firstName.equals(persons.get(i).getFirstName()) && lastName.equals(persons.get(i).getLastName())) {
                persons.remove(i);
                data.setPersons(persons);
                adr.modifyData(data);
                logger.info("Person deleted from Database successfully");
                return true;
            }
        }
        logger.error("No Person with this Name found in DataBase, nothing to delete");
        return false;
    }
}
