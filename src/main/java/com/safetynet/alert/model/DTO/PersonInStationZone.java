package com.safetynet.alert.model.DTO;
import com.safetynet.alert.model.Person;
import lombok.Data;
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

}
