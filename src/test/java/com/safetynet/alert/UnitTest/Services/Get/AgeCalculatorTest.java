package com.safetynet.alert.UnitTest.Services.Get;

import com.safetynet.alert.UnitTest.Services.TestDataConstant;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.AgeCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgeCalculatorTest {
    @Mock
    AllDataRepository adr;
    @InjectMocks
    AgeCalculator agecalcul;
    @Test
    void testGetAge() throws FileNotFoundException {
        when(adr.getMedicalRecords()).thenReturn(TestDataConstant.testMedicalRecordsList);
        int age1 = agecalcul.getAge(TestDataConstant.person1);
        int age2 = agecalcul.getAge(TestDataConstant.person3);
        Assertions.assertEquals(39, age1); //will be false after the 6th of March
        Assertions.assertEquals(11, age2); //will be false after the 18th of February
    }
    @Test
    void testGetBirthDate() throws FileNotFoundException {
        when(adr.getMedicalRecords()).thenReturn(TestDataConstant.testMedicalRecordsList);
        String date1 = agecalcul.getBirthDate(TestDataConstant.person1);
        String date2 = agecalcul.getBirthDate(TestDataConstant.person3);
        Assertions.assertEquals("03/06/1984", date1);
        Assertions.assertEquals("02/18/2012", date2);
    }
}
