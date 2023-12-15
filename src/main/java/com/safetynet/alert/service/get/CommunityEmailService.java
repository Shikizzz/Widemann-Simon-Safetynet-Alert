package com.safetynet.alert.service.get;

import com.safetynet.alert.model.DTO.CommunityEmailDTO.CityEmails;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.AllDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
@Component
public class CommunityEmailService {
    private static Logger logger = LoggerFactory.getLogger(CommunityEmailService.class);
    @Autowired
    private AllDataRepository adr;

    private ArrayList<Person> getPersons() throws FileNotFoundException {
        return adr.getPersons();
    }

    public CityEmails getCityEmail(String city) throws FileNotFoundException {
        ArrayList<String> eMails = new ArrayList<>();
        ArrayList<Person> persons = getPersons();
        for(int i=0; i< persons.size(); i++){
            if(persons.get(i).getCity().equals(city)){ // to scale with a bigger Database
                eMails.add(persons.get(i).getEmail());
            }
        }
        ArrayList<String> eMailsNoDouble = new ArrayList<>(new HashSet<>(eMails)); //removes doubles
        CityEmails cityEmails = new CityEmails();
        cityEmails.setEMail(eMailsNoDouble);
        if (eMailsNoDouble.size()==0){
            logger.error("No one found in this city. Spelling may be wrong");
        }
        else logger.info("Email addresses of citizen from "+city+" retrieved");
        return cityEmails;
    }

}
