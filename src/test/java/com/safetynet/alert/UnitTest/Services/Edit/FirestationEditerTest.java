package com.safetynet.alert.UnitTest.Services.Edit;

import com.safetynet.alert.UnitTest.Services.TestDataConstant;
import com.safetynet.alert.model.AllData;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.edit.FirestationEditer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.FileNotFoundException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FirestationEditerTest {
    @Mock
    AllDataRepository adr;
    @InjectMocks
    FirestationEditer service;

    @BeforeEach
    void setup() throws FileNotFoundException {
        //doNothing().when(adr).modifyData(any(AllData.class));
        when(adr.getData()).thenReturn(TestDataConstant.testData);
    }/*
    @AfterEach
    void teardown(){
        TestDataConstant.testData.setFirestations(TestDataConstant.testFireStationsList); //useless
    }*/

    @Test
    void postNewFirestationTest() throws FileNotFoundException {
        service.postFirestation("test", "42");
        verify(adr, times(1)).modifyData(any(AllData.class));
        service.deleteStationByAddress("test");
    }
    @Test
    void postExistingFirestationTest() throws FileNotFoundException {
        service.postFirestation("1509 Culver St", "3");
        verify(adr, times(0)).modifyData(any(AllData.class));
    }
    @Test
    void putExistingFirestationTest() throws FileNotFoundException {
        service.putFirestation("1509 Culver St", "2");
        verify(adr, times(1)).modifyData(any(AllData.class));
        service.putFirestation("1509 Culver St", "3");
    }
    @Test
    void putNewFirestationTest() throws FileNotFoundException {
        service.putFirestation("TestAddress", "43");
        verify(adr, times(0)).modifyData(any(AllData.class));
    }
    @Test
    void deleteStationByNumberTest()throws FileNotFoundException {
        service.postFirestation("test", "42");
        service.postFirestation("test2", "42");
        service.deleteStationByNumber("42");
        verify(adr, times(3)).modifyData(any(AllData.class));
        Assertions.assertEquals(3, TestDataConstant.testData.getFirestations().size());
    }
    @Test
    void deleteStationByAddressTest()throws FileNotFoundException {
        service.postFirestation("test", "42");
        service.deleteStationByAddress("test");
        verify(adr, times(2)).modifyData(any(AllData.class));
        Assertions.assertEquals(3, TestDataConstant.testData.getFirestations().size());
    }
    @Test
    void deleteNonExistentStationByNumberTest()throws FileNotFoundException {
        service.deleteStationByNumber("666");
        verify(adr, times(0)).modifyData(any(AllData.class));
    }
    @Test
    void deleteNonExistentStationByAddressTest()throws FileNotFoundException {
        service.deleteStationByAddress("<ZESRG<FH");
        verify(adr, times(0)).modifyData(any(AllData.class));
    }
}
