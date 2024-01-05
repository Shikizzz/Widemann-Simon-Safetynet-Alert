package com.safetynet.alert.UnitTest.Services.Get;

import com.safetynet.alert.UnitTest.Services.TestDataConstant;
import com.safetynet.alert.model.DTO.CommunityEmailDTO.CityEmails;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.get.CommunityEmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunityEmailServiceTest {
    @Mock
    AllDataRepository adr;
    @InjectMocks
    CommunityEmailService service;
    @Test
    void testGetCityEmail() throws FileNotFoundException {
        when(adr.getPersons()).thenReturn(TestDataConstant.testPersonsList);
        CityEmails cityEmails = service.getCityEmail("Culver");
        Assertions.assertEquals(3, cityEmails.getEMail().size()); //no doubles in the list
    }
}
