package com.safetynet.alert.UnitTest.Services.Get;

import com.safetynet.alert.UnitTest.Services.TestDataConstant;
import com.safetynet.alert.model.DTO.AddressDTO.AddressInfos;
import com.safetynet.alert.model.DTO.AddressDTO.MedicalInfos;
import com.safetynet.alert.model.DTO.AddressDTO.Resident;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.AgeCalculator;
import com.safetynet.alert.service.get.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    AgeCalculator agecalcul;
    @Mock
    AllDataRepository adr;
    @InjectMocks
    AddressService service;

    @Test
    void testGetAllAddressInfos() throws FileNotFoundException {
        when(adr.getPersons()).thenReturn(TestDataConstant.testPersonsList);
        when(agecalcul.getAge(any(Person.class))).thenReturn(20);
        when(adr.getMedicalRecords()).thenReturn(TestDataConstant.testMedicalRecordsList);
        when(adr.getFireStations()).thenReturn(TestDataConstant.testFireStationsList);
        AddressInfos infos = service.getAllAddressInfos("1509 Culver St");
        Assertions.assertEquals(5, infos.getPeopleList().size());
        Assertions.assertEquals("3", infos.getFireStationNumber());
    }
    @Test
    void testGetAllPeopleList() throws FileNotFoundException {
        when(adr.getPersons()).thenReturn(TestDataConstant.testPersonsList);
        when(agecalcul.getAge(any(Person.class))).thenReturn(20);
        when(adr.getMedicalRecords()).thenReturn(TestDataConstant.testMedicalRecordsList);
        ArrayList<Resident> peopleList1 = service.getAllPeopleList("1509 Culver St");
        ArrayList<Resident> peopleList2 = service.getAllPeopleList("29 15th St");
        Assertions.assertEquals(5, peopleList1.size());
        Assertions.assertEquals(1, peopleList2.size());

    }
    @Test
    void testGetPersonMedicalInfos() throws FileNotFoundException {
        when(adr.getMedicalRecords()).thenReturn(TestDataConstant.testMedicalRecordsList);
        MedicalInfos medicalInfos = service.getPersonMedicalInfos(TestDataConstant.testPersonsList.get(0));
        Assertions.assertEquals(TestDataConstant.medications1, medicalInfos.getMedications());
        Assertions.assertEquals(TestDataConstant.allergies1, medicalInfos.getAllergies());
    }
    @Test
    void testGetStationNumber() throws FileNotFoundException {
        when(adr.getFireStations()).thenReturn(TestDataConstant.testFireStationsList);
        String stationNumber = service.getStationNumber("1509 Culver St");
        String stationNumberBis = service.getStationNumber("29 15th St");
        Assertions.assertEquals("3", stationNumber);
        Assertions.assertEquals("2", stationNumberBis);
    }
}