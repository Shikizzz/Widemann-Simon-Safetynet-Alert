package com.safetynet.alert.UnitTest.Services.Edit;

import com.safetynet.alert.UnitTest.Services.TestDataConstant;
import com.safetynet.alert.model.AllData;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.AllDataRepository;
import com.safetynet.alert.service.edit.MedicalRecordEditer;
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
public class MedicalRecordEditerTest {
    @Mock
    AllDataRepository adr;
    @InjectMocks
    MedicalRecordEditer service;

    @BeforeEach
    void setup() throws FileNotFoundException {
        when(adr.getData()).thenReturn(TestDataConstant.testData);
    }
    @Test
    void postNewMedicalRecordTest() throws FileNotFoundException {
        String medications[] = {"medic"};
        String allergies[] = {"allergie"};
        MedicalRecord testMedicalRecord = new MedicalRecord("John", "Doe", "01/01/2001", medications, allergies);
        service.postMedicalRecord(testMedicalRecord);
        verify(adr, times(1)).modifyData(any(AllData.class));
        service.deleteMedicalRecord("John", "Doe");
    }
    @Test
    void postExistingMedicalRecordTest() throws FileNotFoundException {
        service.postMedicalRecord(TestDataConstant.medicalRecord1);
        verify(adr, times(0)).modifyData(any(AllData.class));
        service.deleteMedicalRecord("John", "Doe");
    }
    @Test
    void putExistingMedicalRecordTest() throws FileNotFoundException {
        String medications[] = {"medic"};
        String allergies[] = {"allergie"};
        MedicalRecord testMedicalRecord1 = new MedicalRecord("John", "Doe", "01/01/2001", medications, allergies);
        MedicalRecord testMedicalRecord2 = new MedicalRecord("John", "Doe", "02/02/2002", medications, allergies);
        service.postMedicalRecord(testMedicalRecord1);
        service.putMedicalRecord(testMedicalRecord2);
        verify(adr, times(2)).modifyData(any(AllData.class));
        service.deleteMedicalRecord("John", "Doe");
    }
    @Test
    void putNewMedicalRecordTest() throws FileNotFoundException {
        String medications[] = {"medic"};
        String allergies[] = {"allergie"};
        MedicalRecord testMedicalRecord = new MedicalRecord("John", "Doe", "01/01/2001", medications, allergies);
        service.putMedicalRecord(testMedicalRecord);
        verify(adr, times(0)).modifyData(any(AllData.class));
    }
    @Test
    void DeleteExistingMedicalRecordTest() throws FileNotFoundException {
        String medications[] = {"medic"};
        String allergies[] = {"allergie"};
        MedicalRecord testMedicalRecord = new MedicalRecord("John", "Doe", "01/01/2001", medications, allergies);
        service.postMedicalRecord(testMedicalRecord);
        service.deleteMedicalRecord("John", "Doe");
        verify(adr, times(2)).modifyData(any(AllData.class));
    }
    @Test
    void DeleteNewMedicalRecordTest() throws FileNotFoundException {
        service.deleteMedicalRecord("John", "Doe");
        verify(adr, times(0)).modifyData(any(AllData.class));
    }
}
