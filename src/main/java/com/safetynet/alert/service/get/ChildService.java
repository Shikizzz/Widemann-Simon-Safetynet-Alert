package com.safetynet.alert.service.get;

import com.safetynet.alert.model.DTO.ChildDTO.ChildInfo;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.model.DTO.ChildDTO.ChildList;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.AgeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import static com.safetynet.alert.model.DTO.FireStationDTO.PeopleIdentity.personToPeopleIdentityArray;

@Component
public class ChildService {

    private static Logger logger = LoggerFactory.getLogger(ChildService.class);
    @Autowired
    private AllDataRepository adr;
    @Autowired
    private AgeCalculator ageCalcul;

    private ArrayList<Person> getPersons() throws FileNotFoundException {
        return adr.getPersons();
    }

    public ChildList getChildrenInAddress(String address) throws FileNotFoundException {
        ArrayList<Person> personsInAddress = getPeopleInAddress(address);
        ArrayList<ChildInfo> childrenInAddress = new ArrayList<>();
        for (int i=0; i<personsInAddress.size(); i++){
            if(ageCalcul.getAge(personsInAddress.get(i))<=18){
                ChildInfo child = new ChildInfo();
                child.setFirstName(personsInAddress.get(i).getFirstName());
                child.setLastName(personsInAddress.get(i).getLastName());
                child.setAge(ageCalcul.getAge(personsInAddress.get(i)));
                ArrayList<Person> personsInAddressTemp = getPeopleInAddress(address);
                personsInAddressTemp.remove(i);
                child.setOthers(personToPeopleIdentityArray(personsInAddressTemp));
                childrenInAddress.add(child);
            }
        }
        ChildList list = new ChildList();
        list.setChildList(childrenInAddress);
        if(childrenInAddress.size()>0) {
            logger.info((childrenInAddress.size()) + " children found at this Address");
        }
        return list;
    }
    private ArrayList<Person> getPeopleInAddress(String address) throws FileNotFoundException {
        ArrayList<Person> allPersons = getPersons();
        ArrayList<Person> personsInAddress = new ArrayList<>();
        for(int i=0; i<allPersons.size(); i++){
            if(allPersons.get(i).getAddress().equals(address)){
                personsInAddress.add(allPersons.get(i));
            }
        }
        if(personsInAddress.size()==0){
            logger.error("No one found in this address. Address may be wrong.");
        }
        return personsInAddress;
    }

}
