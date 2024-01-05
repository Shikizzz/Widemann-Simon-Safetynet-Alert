package com.safetynet.alert.UnitTest.Services.Get;

import com.safetynet.alert.UnitTest.Services.TestDataConstant;
import com.safetynet.alert.model.DTO.PersonInfoDTO.AllPersonInfo;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.AgeCalculator;
import com.safetynet.alert.service.get.AddressService;
import com.safetynet.alert.service.get.PersonInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonInfoServiceTest {
    @Mock
    AgeCalculator agecalcul;
    @Mock
    AllDataRepository adr;
    @Mock
    AddressService addressService;
    @InjectMocks
    PersonInfoService service;

    @Test
    void testGetAllPersonInCityInfo() throws FileNotFoundException {
        when(adr.getPersons()).thenReturn(TestDataConstant.testPersonsList);
        when(addressService.getPersonMedicalInfos(TestDataConstant.person2)).thenReturn(TestDataConstant.medicalInfos2);
        when(agecalcul.getAge(eq(TestDataConstant.person2))).thenReturn(34);
        AllPersonInfo persons = service.getAllPersonInCityInfo("Jacob", "Boyd");
        Assertions.assertEquals(1, persons.getPersonsInfo().size());
        Assertions.assertEquals(TestDataConstant.medicalInfos2, persons.getPersonsInfo().get(0).getMedicalInfos());
    }
}
