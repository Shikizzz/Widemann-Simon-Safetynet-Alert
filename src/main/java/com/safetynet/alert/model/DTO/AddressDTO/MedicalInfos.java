package com.safetynet.alert.model.DTO.AddressDTO;

import lombok.Data;

@Data
public class MedicalInfos {
    private String[] medications;
    private String[] allergies;
}
