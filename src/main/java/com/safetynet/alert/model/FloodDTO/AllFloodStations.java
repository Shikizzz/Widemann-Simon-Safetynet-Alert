package com.safetynet.alert.model.FloodDTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AllFloodStations {
    private ArrayList<FloodStation> floodStationsList = new ArrayList<FloodStation>();
}
