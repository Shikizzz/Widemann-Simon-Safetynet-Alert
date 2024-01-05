package com.safetynet.alert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class FireStation {

    private String address;
    private String station;

    public FireStation(String address, String station) {
        this.address = address;
        this.station = station;
    }

    public FireStation() {
    }
}
