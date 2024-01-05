package com.safetynet.alert.UnitTest.Services.Get;

import com.safetynet.alert.UnitTest.Services.TestDataConstant;
import com.safetynet.alert.model.DTO.FireStationDTO.AllPersonsInStationZone;
import com.safetynet.alert.model.DTO.PhoneDTO.PhoneList;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.AgeCalculator;
import com.safetynet.alert.service.get.FireStationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {
    @Mock
    AgeCalculator agecalcul;
    @Mock
    AllDataRepository adr;
    @InjectMocks
    FireStationService service;
    @Test
    void testGetAllPersonsInStationZone() throws FileNotFoundException {
        when(adr.getPersons()).thenReturn(TestDataConstant.testPersonsList);
        when(adr.getFireStations()).thenReturn(TestDataConstant.testFireStationsList);
        when(agecalcul.getAge(eq(TestDataConstant.person1))).thenReturn(39);
        when(agecalcul.getAge(eq(TestDataConstant.person2))).thenReturn(34);
        when(agecalcul.getAge(eq(TestDataConstant.person3))).thenReturn(11);
        when(agecalcul.getAge(eq(TestDataConstant.person4))).thenReturn(6);
        when(agecalcul.getAge(eq(TestDataConstant.person5))).thenReturn(36);
        when(agecalcul.getAge(eq(TestDataConstant.person6))).thenReturn(39);
        AllPersonsInStationZone persons1 = service.getAllPersonsInStationZone("3");
        AllPersonsInStationZone persons2 = service.getAllPersonsInStationZone("2");
        Assertions.assertEquals(5, persons1.getPersons().size());
        Assertions.assertEquals(2, persons1.getChildrenNumber());
        Assertions.assertEquals(3, persons1.getAdultsNumber());
        Assertions.assertEquals(1, persons2.getPersons().size());
        Assertions.assertEquals(0, persons2.getChildrenNumber());
        Assertions.assertEquals(1, persons2.getAdultsNumber());
    }
    @Test
    void testGetPersonsInStationZone() throws FileNotFoundException {
        when(adr.getPersons()).thenReturn(TestDataConstant.testPersonsList);
        when(adr.getFireStations()).thenReturn(TestDataConstant.testFireStationsList);
        ArrayList<Person> persons1 = service.getPersonsInStationZone("3");
        ArrayList<Person> persons2 = service.getPersonsInStationZone("2");
        Assertions.assertEquals(5, persons1.size());
        Assertions.assertEquals(1, persons2.size());
    }
    @Test
    void testFilterStationsByZone() throws FileNotFoundException {
        when(adr.getFireStations()).thenReturn(TestDataConstant.testFireStationsList);
        ArrayList<FireStation> fireStations1 = service.filterStationsByZone("3");
        ArrayList<FireStation> fireStations2 = service.filterStationsByZone("2");
        Assertions.assertEquals(2, fireStations1.size());
        Assertions.assertEquals(1, fireStations2.size());
    }
    @Test
    void testGetPhoneNumberInStationZone() throws FileNotFoundException {
        when(adr.getPersons()).thenReturn(TestDataConstant.testPersonsList);
        when(adr.getFireStations()).thenReturn(TestDataConstant.testFireStationsList);
        PhoneList phoneList1 = service.getPhoneNumberInStationZone("3");
        PhoneList phoneList2 = service.getPhoneNumberInStationZone("2");
        Assertions.assertEquals(3, phoneList1.getPhoneNumbers().size());
        Assertions.assertEquals(1, phoneList2.getPhoneNumbers().size());
        Assertions.assertEquals("841-874-6513", phoneList2.getPhoneNumbers().get(0));
    }
}
