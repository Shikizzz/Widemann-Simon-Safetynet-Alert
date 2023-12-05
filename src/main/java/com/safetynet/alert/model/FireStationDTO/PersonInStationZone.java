package com.safetynet.alert.model.FireStationDTO;
import com.safetynet.alert.model.DAO.Person;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PersonInStationZone {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;

    public static PersonInStationZone personToPersonInStationZone(Person person){ //converter
        PersonInStationZone p = new PersonInStationZone();
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setAddress(person.getAddress());
        p.setCity(person.getCity());
        p.setZip(person.getZip());
        p.setPhone(person.getPhone());
        return p;
    }

    public static ArrayList<PersonInStationZone> personToPersonInStationZoneArray(ArrayList<Person> persons) {
        ArrayList<PersonInStationZone> p = new ArrayList<>();
        for (int i=0; i< persons.size(); i++){
            p.add(personToPersonInStationZone(persons.get(i)));
        }
        return p;
    }
}
