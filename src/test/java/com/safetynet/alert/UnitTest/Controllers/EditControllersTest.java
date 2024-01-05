package com.safetynet.alert.UnitTest.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.controller.edit.FirestationController;
import com.safetynet.alert.controller.edit.MedicalRecordController;
import com.safetynet.alert.controller.edit.PersonController;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.edit.FirestationEditer;
import com.safetynet.alert.service.edit.MedicalRecordEditer;
import com.safetynet.alert.service.edit.PersonEditer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {FirestationController.class, MedicalRecordController.class, PersonController.class})
public class EditControllersTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomResponseEntity customResponseEntity;
    @MockBean
    private FirestationEditer firestationEditer;
    @MockBean
    private MedicalRecordEditer medicalRecordEditer;
    @MockBean
    private PersonEditer person;

    @Test
    public void testAddFirestation() throws Exception {
        mockMvc.perform(post("/firestation?address=<address>&stationNumber=<stationNumber>")).andExpect(status().isOk());
    }
    @Test
    public void testPutFirestation() throws Exception {
        mockMvc.perform(put("/firestation?address=<address>&stationNumber=<stationNumber>")).andExpect(status().isOk());
    }
    @Test
    public void testDeleteStationByAddress() throws Exception {
        mockMvc.perform(delete("/firestation/address?address=<address>")).andExpect(status().isOk());
    }
    @Test
    public void testDeleteStationByNumber() throws Exception {
        mockMvc.perform(delete("/firestation/stationNumber?stationNumber=<stationNumber>")).andExpect(status().isOk());
    }

    @Test
    public void testAddMedicalRecord() throws Exception {
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
        mockMvc.perform(post("/medicalRecord").contentType(APPLICATION_JSON_UTF8)
                .content(medicalRecord)).andExpect(status().isOk());
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
        mockMvc.perform(put("/medicalRecord").contentType(APPLICATION_JSON_UTF8)
                .content(medicalRecord)).andExpect(status().isOk());
    }
    @Test
    public void testDeleteMedicalRecord() throws Exception {
        mockMvc.perform(delete("/medicalRecord?firstName=John&lastName=Doe")).andExpect(status().isOk());
    }

    @Test
    public void testAddPerson() throws Exception {
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
        mockMvc.perform(post("/person").contentType(APPLICATION_JSON_UTF8)
                .content(person)).andExpect(status().isOk());
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
        mockMvc.perform(put("/person").contentType(APPLICATION_JSON_UTF8)
                .content(person)).andExpect(status().isOk());
    }
    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/person?firstName=John&lastName=Doe")).andExpect(status().isOk());
    }
}
