package com.safetynet.alert.UnitTest.Services.Edit;

import com.safetynet.alert.UnitTest.Services.TestDataConstant;
import com.safetynet.alert.model.AllData;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.edit.PersonEditer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonEditerTest {
    @Mock
    AllDataRepository adr;
    @InjectMocks
    PersonEditer service;

    @BeforeEach
    void setup() throws FileNotFoundException {
        when(adr.getData()).thenReturn(TestDataConstant.testData);
    }
    @Test
    void postNewPersonTest() throws FileNotFoundException {
        Person testPerson = new Person("John", "Doe", "TestAddress", "Culver", "97451", "841-000-000", "johndoe@email.com");
        service.postPerson(testPerson);
        verify(adr, times(1)).modifyData(any(AllData.class));
        service.deletePerson("John", "Doe");
    }
    @Test
    void postExistingPersonTest() throws FileNotFoundException {
        Person testPerson = new Person("John", "Boyd", "TestAddress2", "Culver", "97451", "841-000-001", "johndoe2@email.com");
        service.postPerson(testPerson);
        verify(adr, times(0)).modifyData(any(AllData.class));
    }
    @Test
    void putExistingPersonTest() throws FileNotFoundException {
        Person testPerson = new Person("John", "Doe", "TestAddress", "Culver", "97451", "841-000-000", "johndoe@email.com");
        Person testPerson2 = new Person("John", "Doe", "TestAddress2", "Culver", "97451", "841-000-001", "johndoe2@email.com");
        service.postPerson(testPerson);
        service.putPerson(testPerson2);
        verify(adr, times(2)).modifyData(any(AllData.class));
        service.deletePerson("John", "Doe");
    }
    @Test
    void putNewPersonTest() throws FileNotFoundException {
        Person testPerson = new Person("John", "Doe", "TestAddress", "Culver", "97451", "841-000-000", "johndoe@email.com");
        service.putPerson(testPerson);
        verify(adr, times(0)).modifyData(any(AllData.class));
    }
    @Test
    void DeleteExistingPersonTest() throws FileNotFoundException {
        Person testPerson = new Person("John", "Doe", "TestAddress", "Culver", "97451", "841-000-000", "johndoe@email.com");
        service.postPerson(testPerson);
        service.deletePerson("John", "Doe");
        verify(adr, times(2)).modifyData(any(AllData.class));
    }
    @Test
    void DeleteNewPersonTest() throws FileNotFoundException {
        service.deletePerson("John", "Doe");
        verify(adr, times(0)).modifyData(any(AllData.class));
    }
}
