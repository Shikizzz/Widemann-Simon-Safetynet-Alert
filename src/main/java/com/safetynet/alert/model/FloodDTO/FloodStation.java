package com.safetynet.alert.model.FloodDTO;

import lombok.Data;

import java.util.ArrayList;
@Data
public class FloodStation {
    private String stationNumber;
    private ArrayList<Foyer> foyers = new ArrayList<Foyer>();
}
