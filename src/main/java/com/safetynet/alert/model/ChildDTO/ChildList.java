package com.safetynet.alert.model.ChildDTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ChildList {
    private ArrayList<ChildInfo> childList = new ArrayList<>();
}
