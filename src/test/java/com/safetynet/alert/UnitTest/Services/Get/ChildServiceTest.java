package com.safetynet.alert.UnitTest.Services.Get;

import com.safetynet.alert.UnitTest.Services.TestDataConstant;
import com.safetynet.alert.model.DTO.ChildDTO.ChildList;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.AgeCalculator;
import com.safetynet.alert.service.get.ChildService;
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
public class ChildServiceTest {
    @Mock
    AgeCalculator agecalcul;
    @Mock
    AllDataRepository adr;
    @InjectMocks
    ChildService service;

    @Test
    void testGetChildrenInAddress() throws FileNotFoundException {
        when(adr.getPersons()).thenReturn(TestDataConstant.testPersonsList);
        when(agecalcul.getAge(eq(TestDataConstant.person1))).thenReturn(39);
        when(agecalcul.getAge(eq(TestDataConstant.person2))).thenReturn(34);
        when(agecalcul.getAge(eq(TestDataConstant.person3))).thenReturn(11);
        when(agecalcul.getAge(eq(TestDataConstant.person4))).thenReturn(6);
        when(agecalcul.getAge(eq(TestDataConstant.person5))).thenReturn(36);
        ChildList childList = service.getChildrenInAddress("1509 Culver St");
        Assertions.assertEquals(2, childList.getChildList().size());
        Assertions.assertEquals("Tenley", childList.getChildList().get(0).getFirstName());
        Assertions.assertEquals(4, childList.getChildList().get(0).getOthers().size());
    }
    @Test
    void testGetPeopleInAddress() throws FileNotFoundException {
        when(adr.getPersons()).thenReturn(TestDataConstant.testPersonsList);
        ArrayList<Person> persons = service.getPeopleInAddress("1509 Culver St");
        Assertions.assertEquals(5, persons.size());
    }
}
