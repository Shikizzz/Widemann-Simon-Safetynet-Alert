package com.safetynet.alert.service;

import com.safetynet.alert.model.CommunityEmailDTO.CityEmails;
import com.safetynet.alert.model.DAO.Person;
import com.safetynet.alert.repository.AllDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashSet;
@Component
public class CommunityEmailService {
    @Autowired
    private AllDataRepository adr;

    private ArrayList<Person> getPersons() throws Exception {
        return adr.getPersons();
    }

    public CityEmails getCityEmail(String city) throws Exception {
        ArrayList<String> eMails = new ArrayList<>();
        ArrayList<Person> persons = getPersons();
        for(int i=0; i< persons.size(); i++){
            if(persons.get(i).getCity().equals(city)){
                eMails.add(persons.get(i).getEmail());
            }
        }
        ArrayList<String> eMailsNoDouble = new ArrayList<>(new HashSet<>(eMails)); //removes doubles
        CityEmails cityEmails = new CityEmails();
        cityEmails.setEMail(eMailsNoDouble);
        return cityEmails;
    }

}
