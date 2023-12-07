package com.safetynet.alert.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.DAO.AllData;
import com.safetynet.alert.model.DAO.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;

@Data
@Repository
public class PersonRepository {
    @Autowired
    AllDataRepository adr;

    public void postPerson(Person person) throws IOException {
        AllData data = adr.getData();
        ArrayList<Person> persons=  adr.getPersons();
        for(int i=0; i<persons.size(); i++) {
            if (person.getFirstName().equals(persons.get(i).getFirstName())&&person.getLastName().equals(persons.get(i).getLastName())){
                //TODO Add a logger
                return;  //we don't add if the person is already in DB.
            }
        }
        persons.add(person);
        data.setPersons(persons);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
    }

    public void putPerson(Person person) throws IOException {
        AllData data = adr.getData();
        ArrayList<Person> persons=  adr.getPersons();
        boolean found = false;
        for(int i=0; i<persons.size(); i++) {
            if (person.getFirstName().equals(persons.get(i).getFirstName())&&person.getLastName().equals(persons.get(i).getLastName())){
                found = true;
                persons.get(i).setAddress(person.getAddress());
                persons.get(i).setCity(person.getCity());
                persons.get(i).setZip(person.getZip());
                persons.get(i).setPhone(person.getPhone());
                persons.get(i).setEmail(person.getEmail());
                break;
            }
        }
        if (!found){}//TODO Add a logger, person not found in DB
        data.setPersons(persons);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
    }

    public void deletePerson(String firstName, String lastName) throws IOException {
        AllData data = adr.getData();
        ArrayList<Person> persons=  adr.getPersons();
        boolean found = false;
        for(int i=0; i<persons.size(); i++) {
            if (firstName.equals(persons.get(i).getFirstName())&&lastName.equals(persons.get(i).getLastName())){
                found = true;
                persons.remove(i);
                break;
            }
        }
        if (!found){}//TODO Add a logger, person not found in DB
        data.setPersons(persons);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(adr.getFile(), data);
    }

}
