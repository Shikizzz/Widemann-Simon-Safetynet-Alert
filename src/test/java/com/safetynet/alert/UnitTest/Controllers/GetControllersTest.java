package com.safetynet.alert.UnitTest.Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.controller.get.*;
import com.safetynet.alert.service.get.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {AddressController.class, ChildController.class, CommunityEmailController.class, FireStationController.class, FloodController.class, PersonInfoController.class, PhoneController.class}) //loads all Spring controllers
public class GetControllersTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomResponseEntity customResponseEntity;
    @MockBean
    private AddressService addressService;
    @MockBean
    private ChildService childService;
    @MockBean
    private CommunityEmailService communityEmailService;
    @MockBean
    private FireStationService fireStationService;
    @MockBean
    private FloodService floodService;
    @MockBean
    private PersonInfoService personInfoService;


    @Test
    public void testGetAllAddressInfos() throws Exception {
        mockMvc.perform(get("/fire?address=<address>")).andExpect(status().isOk());
    }
    @Test
    public void testGetChildrenInAddress() throws Exception {
        mockMvc.perform(get("/childAlert?address=<address>")).andExpect(status().isOk());
    }
    @Test
    public void testGetCityEmail() throws Exception {
        mockMvc.perform(get("/communityEmail?city=<city>")).andExpect(status().isOk());
    }
    @Test
    public void testGetAllPersonsInStationZone() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=<station_number>")).andExpect(status().isOk());
    }
    @Test
    public void testGetFloodInfo() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=<a list of station_numbers>")).andExpect(status().isOk());
    }
    @Test
    public void testGetAllPersonInCityInfo() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=<firstName>&lastName=<lastName>")).andExpect(status().isOk());
    }
    @Test
    public void testGetPhoneNumberInStationZone() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=<firestation_number>")).andExpect(status().isOk());
    }
}
