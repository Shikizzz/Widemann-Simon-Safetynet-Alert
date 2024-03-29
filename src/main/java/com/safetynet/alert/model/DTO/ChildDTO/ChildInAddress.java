package com.safetynet.alert.model.DTO.ChildDTO;

import com.safetynet.alert.model.DTO.FireStationDTO.PeopleIdentity;
import lombok.Data;

import java.util.ArrayList;
@Data
public class ChildInAddress {

    private ArrayList<ChildInfo> children = new ArrayList<>();
    private ArrayList<PeopleIdentity> adults = new ArrayList<>();

    public ChildInAddress(ArrayList<ChildInfo> children, ArrayList<PeopleIdentity> adults){
        this.children = children;
        this.adults = adults;
    }
}
