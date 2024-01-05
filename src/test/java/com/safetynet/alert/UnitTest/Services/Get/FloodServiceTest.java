package com.safetynet.alert.UnitTest.Services.Get;

import com.safetynet.alert.UnitTest.Services.TestDataConstant;
import com.safetynet.alert.model.DTO.FloodDTO.AllFloodStations;
import com.safetynet.alert.model.DTO.FloodDTO.Foyer;
import com.safetynet.alert.service.get.AddressService;
import com.safetynet.alert.service.get.FireStationService;
import com.safetynet.alert.service.get.FloodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FloodServiceTest {
    @Mock
    FireStationService fireStationService;
    @Mock
    AddressService addressService;
    @InjectMocks
    FloodService service;
    @Test
    void testGetFloodInfo() throws FileNotFoundException {
        when(fireStationService.filterStationsByZone("3")).thenReturn(TestDataConstant.fireStationsListWithNumber3);
        when(fireStationService.filterStationsByZone("2")).thenReturn(TestDataConstant.fireStationsListWithNumber2);
        when(addressService.getAllPeopleList("1509 Culver St")).thenReturn(TestDataConstant.residents1);
        when(addressService.getAllPeopleList("29 15th St")).thenReturn(TestDataConstant.residents2);
        when(addressService.getAllPeopleList("834 Binoc Ave")).thenReturn(TestDataConstant.residents3);
        ArrayList<String> stations = new ArrayList<>(Arrays.asList("3","2"));
        AllFloodStations floodStations = service.getFloodInfo(stations);
        Assertions.assertEquals(2, floodStations.getFloodStationsList().size());
        Assertions.assertEquals("3", floodStations.getFloodStationsList().get(0).getStationNumber());
        Assertions.assertEquals(2, floodStations.getFloodStationsList().get(0).getFoyers().size());
        Assertions.assertEquals(5, floodStations.getFloodStationsList().get(0).getFoyers().get(0).getResidents().size());
        Assertions.assertEquals(0, floodStations.getFloodStationsList().get(0).getFoyers().get(1).getResidents().size());
    }
    @Test
    void testGetFloodStationInfo() throws FileNotFoundException {
        when(fireStationService.filterStationsByZone("3")).thenReturn(TestDataConstant.fireStationsListWithNumber3);
        when(addressService.getAllPeopleList("1509 Culver St")).thenReturn(TestDataConstant.residents1);
        when(addressService.getAllPeopleList("834 Binoc Ave")).thenReturn(TestDataConstant.residents3);
        ArrayList<Foyer> foyers = service.getFloodStationInfo("3");
        Assertions.assertEquals(2, foyers.size());
        Assertions.assertEquals(5, foyers.get(0).getResidents().size());
        Assertions.assertEquals(0, foyers.get(1).getResidents().size());
    }
}
