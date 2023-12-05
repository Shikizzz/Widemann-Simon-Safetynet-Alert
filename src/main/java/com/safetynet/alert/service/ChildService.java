package com.safetynet.alert.service;

import com.safetynet.alert.model.DAO.AllData;
import com.safetynet.alert.model.ChildDTO.ChildInfo;
import com.safetynet.alert.model.DAO.Person;
import com.safetynet.alert.model.ChildDTO.ChildList;
import com.safetynet.alert.repository.AllDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.safetynet.alert.model.FireStationDTO.PeopleIdentity.personToPeopleIdentityArray;

@Component
public class ChildService {
    @Autowired
    private AllDataRepository adr;
    @Autowired
    private AgeCalculatorUtil ageCalcul;

    public AllData getData() throws Exception {
        return adr.getData();
    }
    public ArrayList<Person> getPersons() throws Exception {
        return adr.getPersons();
    }

    public ChildList getChildrenInAddress(String address) throws Exception {
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
        return list;
    }
    public ArrayList<Person> getPeopleInAddress(String address) throws Exception {
        ArrayList<Person> allPersons = getPersons();
        ArrayList<Person> personsInAddress = new ArrayList<>();
        for(int i=0; i<allPersons.size(); i++){
            if(allPersons.get(i).getAddress().equals(address)){
                personsInAddress.add(allPersons.get(i));
            }
        }
        return personsInAddress;
    }

/*
public ChildInAddress getChildrenInAddress(String address) throws Exception { //
        ArrayList<Person> personsInAddress = getPeopleInAddress(address);
        ArrayList<Person> children = new ArrayList<>();
        ArrayList<Person> adults = new ArrayList<>();
        for (int i=0; i<personsInAddress.size(); i++){
            if(ageCalcul.getAge(personsInAddress.get(i))>18){
                adults.add(personsInAddress.get(i));
            }
            else {
                children.add(personsInAddress.get(i));
            }
        }
        ArrayList<ChildInfo> childrenInfos = new ArrayList<>();
        for (int i=0; i<children.size(); i++){
            ChildInfo child = new ChildInfo();
            child.setFirstName(children.get(i).getFirstName());
            child.setLastName(children.get(i).getLastName());
            child.setAge(ageCalcul.getAge(children.get(i)));
            childrenInfos.add(child);
        }
        ArrayList<PeopleIdentity> parentsID = new ArrayList<>();
        for (int i=0; i<adults.size(); i++){
            PeopleIdentity parent = new PeopleIdentity();
            parent.setFirstName(adults.get(i).getFirstName());
            parent.setLastName(adults.get(i).getLastName());
            parentsID.add(parent);
        }
        ChildInAddress result = new ChildInAddress(childrenInfos, parentsID);
        return result;
    }
*/

}
