package com.safetynet.alert.model.FireStationDTO;

import com.safetynet.alert.model.DAO.Person;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PeopleIdentity {
    private String firstName;
    private String lastName;

    public static PeopleIdentity personToPeopleIdentity(Person person){ //converter
        PeopleIdentity p = new PeopleIdentity();
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        return p;
    }

    public static ArrayList<PeopleIdentity> personToPeopleIdentityArray(ArrayList<Person> persons){ //converter
        ArrayList<PeopleIdentity> list = new ArrayList<PeopleIdentity>();
        for(int i=0; i< persons.size(); i++){
            list.add(personToPeopleIdentity(persons.get(i)));
        }
        return list;
    }
}
