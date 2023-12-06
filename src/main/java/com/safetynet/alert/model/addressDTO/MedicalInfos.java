package com.safetynet.alert.model.addressDTO;

import lombok.Data;

@Data
public class MedicalInfos {
    private String[] medications;
    private String[] allergies;
}
