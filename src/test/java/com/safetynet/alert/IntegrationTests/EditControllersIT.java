package com.safetynet.alert.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class EditControllersIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    JsonVerifier verifier;

    @Test
    public void testPostFirestation() throws Exception {
        mockMvc.perform(post("/firestation?address=TestAddress&stationNumber=42"))
                .andExpect(status().isOk());
        Assertions.assertTrue(verifier.fireStationFound("TestAddress", "42"));
        mockMvc.perform(delete("/firestation/stationNumber?stationNumber=42"));
    }
    @Test
    public void testPutFirestation() throws Exception {
        mockMvc.perform(post("/firestation?address=TestAddress&stationNumber=42"));
        mockMvc.perform(put("/firestation?address=TestAddress&stationNumber=43"))
                .andExpect(status().isOk());
        Assertions.assertTrue(verifier.fireStationFound("TestAddress", "43"));
        mockMvc.perform(delete("/firestation/stationNumber?stationNumber=43"));
    }
    @Test
    public void testDeleteFirestationByAddress() throws Exception {
        mockMvc.perform(post("/firestation?address=TestAddress&stationNumber=42"));
        mockMvc.perform(delete("/firestation/address?address=TestAddress"))
                .andExpect(status().isOk());
        Assertions.assertFalse(verifier.fireStationFound("TestAddress", "42"));
    }
    @Test
    public void testDeleteFirestationByNumber() throws Exception {
        mockMvc.perform(post("/firestation?address=TestAddress&stationNumber=42"));
        mockMvc.perform(delete("/firestation/stationNumber?stationNumber=42"))
                .andExpect(status().isOk());
        Assertions.assertFalse(verifier.fireStationFound("TestAddress", "42"));
    }

    @Test
    public void testPostMedicalRecord() throws Exception {
        MedicalRecord testMedicalrecord = new MedicalRecord();
        String medications[] = {"Paracetamol 1g"};
        String allergies[] = {"Peanuts"};
        testMedicalrecord.setFirstName("John");
        testMedicalrecord.setLastName("Doe");
        testMedicalrecord.setBirthdate("01/01/2001");
        testMedicalrecord.setMedications(medications);
        testMedicalrecord.setAllergies(allergies);
        ObjectMapper objectMapper = new ObjectMapper();
        String medicalRecord = objectMapper.writeValueAsString(testMedicalrecord);
        mockMvc.perform(post("/medicalRecord").contentType(APPLICATION_JSON_UTF8).content(medicalRecord))
                .andExpect(status().isOk());
        Assertions.assertTrue(verifier.medicalRecordFound(testMedicalrecord));
        mockMvc.perform(delete("/medicalRecord?firstName=John&lastName=Doe"));
    }
    @Test
    public void testPutMedicalRecord() throws Exception {
        MedicalRecord testMedicalrecord = new MedicalRecord();
        String medications[] = {"Paracetamol 1g"};
        String allergies[] = {"Peanuts"};
        testMedicalrecord.setFirstName("John");
        testMedicalrecord.setLastName("Doe");
        testMedicalrecord.setBirthdate("01/01/2001");
        testMedicalrecord.setMedications(medications);
        testMedicalrecord.setAllergies(allergies);
        ObjectMapper objectMapper = new ObjectMapper();
        String medicalRecord = objectMapper.writeValueAsString(testMedicalrecord);
        mockMvc.perform(post("/medicalRecord").contentType(APPLICATION_JSON_UTF8).content(medicalRecord))
                .andExpect(status().isOk());
        MedicalRecord modifiedMedicalRecord = new MedicalRecord();
        String medications2[] = {"Paracetamol 500mg"};
        String allergies2[] = {"Peanuts"};
        modifiedMedicalRecord.setFirstName("John");
        modifiedMedicalRecord.setLastName("Doe");
        modifiedMedicalRecord.setBirthdate("02/02/2002");
        modifiedMedicalRecord.setMedications(medications);
        modifiedMedicalRecord.setAllergies(allergies);
        medicalRecord = objectMapper.writeValueAsString(modifiedMedicalRecord);
        mockMvc.perform(put("/medicalRecord").contentType(APPLICATION_JSON_UTF8).content(medicalRecord))
                .andExpect(status().isOk());
        Assertions.assertTrue(verifier.medicalRecordFound(modifiedMedicalRecord));
        mockMvc.perform(delete("/medicalRecord?firstName=John&lastName=Doe"));
    }
    @Test
    public void testDeleteMedicalRecord() throws Exception {
        MedicalRecord testMedicalrecord = new MedicalRecord();
        String medications[] = {"Paracetamol 1g"};
        String allergies[] = {"Peanuts"};
        testMedicalrecord.setFirstName("John");
        testMedicalrecord.setLastName("Doe");
        testMedicalrecord.setBirthdate("01/01/2001");
        testMedicalrecord.setMedications(medications);
        testMedicalrecord.setAllergies(allergies);
        ObjectMapper objectMapper = new ObjectMapper();
        String medicalRecord = objectMapper.writeValueAsString(testMedicalrecord);
        mockMvc.perform(post("/medicalRecord").contentType(APPLICATION_JSON_UTF8).content(medicalRecord));
        mockMvc.perform(delete("/medicalRecord?firstName=John&lastName=Doe"))
                .andExpect(status().isOk());
        Assertions.assertFalse(verifier.medicalRecordFound("John","Doe"));
    }

    @Test
    public void testPostPerson() throws Exception {
        Person testPerson = new Person();
        testPerson.setFirstName("John");
        testPerson.setLastName("Doe");
        testPerson.setAddress("4 John Doe Street");
        testPerson.setCity("Culver");
        testPerson.setZip("97451");
        testPerson.setPhone("841-874-1111");
        testPerson.setEmail("jd@email.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String person = objectMapper.writeValueAsString(testPerson);
        mockMvc.perform(post("/person").contentType(APPLICATION_JSON_UTF8).content(person))
                .andExpect(status().isOk());
        Assertions.assertTrue(verifier.personFound(testPerson));
        mockMvc.perform(delete("/person?firstName=John&lastName=Doe"));
    }
    @Test
    public void testPutPerson() throws Exception {
        Person testPerson = new Person();
        testPerson.setFirstName("John");
        testPerson.setLastName("Doe");
        testPerson.setAddress("4 John Doe Street");
        testPerson.setCity("Culver");
        testPerson.setZip("97451");
        testPerson.setPhone("841-874-1111");
        testPerson.setEmail("jd@email.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String person = objectMapper.writeValueAsString(testPerson);
        mockMvc.perform(post("/person").contentType(APPLICATION_JSON_UTF8).content(person));
        Person modifiedPerson = new Person();
        modifiedPerson.setFirstName("John");
        modifiedPerson.setLastName("Doe");
        modifiedPerson.setAddress("4 John Doe Street");
        modifiedPerson.setCity("Culver");
        modifiedPerson.setZip("97451");
        modifiedPerson.setPhone("841-874-2222");
        modifiedPerson.setEmail("jdjd@email.com");
        person = objectMapper.writeValueAsString(modifiedPerson);
        mockMvc.perform(put("/person").contentType(APPLICATION_JSON_UTF8).content(person))
                .andExpect(status().isOk());
        Assertions.assertTrue(verifier.personFound(modifiedPerson));
        mockMvc.perform(delete("/person?firstName=John&lastName=Doe"));
    }
    @Test
    public void testDeletePerson() throws Exception {
        Person testPerson = new Person();
        testPerson.setFirstName("John");
        testPerson.setLastName("Doe");
        testPerson.setAddress("4 John Doe Street");
        testPerson.setCity("Culver");
        testPerson.setZip("97451");
        testPerson.setPhone("841-874-1111");
        testPerson.setEmail("jd@email.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String person = objectMapper.writeValueAsString(testPerson);
        mockMvc.perform(post("/person").contentType(APPLICATION_JSON_UTF8).content(person));
        mockMvc.perform(delete("/person?firstName=John&lastName=Doe"))
                .andExpect(status().isOk());
        Assertions.assertFalse(verifier.personFound("John","Doe"));
    }
}
