package com.safetynet.alert.IntegrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllersIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllAddressInfos() throws Exception {
        mockMvc.perform(get("/fire?address=1509 Culver St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.peopleList.[0].firstName", is("John")))
                .andExpect(jsonPath("$.fireStationNumber", is("3")))
                .andExpect(jsonPath("$.peopleList", hasSize(5)));
    }
    @Test
    public void testGetChildrenInAddress() throws Exception {
        mockMvc.perform(get("/childAlert?address=1509 Culver St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.childList.[0].firstName", is("Tenley")))
                .andExpect(jsonPath("$.childList.[0].others", hasSize(4)))
                .andExpect(jsonPath("$.childList", hasSize(2)));
    }
    @Test
    public void testGetCityEmail() throws Exception {
        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email[0]", is("drk@email.com")));
    }
    @Test
    public void testGetAllPersonsInStationZone() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons", hasSize(6)))
                .andExpect(jsonPath("$.persons[0].firstName", is("Peter")))
                .andExpect(jsonPath("$.adultsNumber", is(5)))
                .andExpect(jsonPath("$.childrenNumber", is(1)));
    }
    @Test
    public void testGetFloodInfo() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.floodStationsList", hasSize(2)))
                .andExpect(jsonPath("$.floodStationsList[0].stationNumber", is("1")))
                .andExpect(jsonPath("$.floodStationsList[0].foyers", hasSize(3)))
                .andExpect(jsonPath("$.floodStationsList[0].foyers[0].address", is("644 Gershwin Cir")));
    }
    @Test
    public void testGetAllPersonInCityInfo() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.personsInfo", hasSize(1)))
                .andExpect(jsonPath("$.personsInfo[0].medicalInfos.medications[0]", is("aznol:350mg")));
    }
    @Test
    public void testGetPhoneNumberInStationZone() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumbers[0]", is("841-874-9845")));
    }
}
