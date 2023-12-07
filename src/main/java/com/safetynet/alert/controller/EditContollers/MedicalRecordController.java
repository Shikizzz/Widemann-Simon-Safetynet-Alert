package com.safetynet.alert.controller.EditContollers;

import com.safetynet.alert.model.DAO.MedicalRecord;
import com.safetynet.alert.repository.MedicalRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class MedicalRecordController {

    private MedicalRecordRepository medicalRecordRepository;
    @PostMapping("/medicalRecord")
    public void addMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
        medicalRecordRepository.postMedicalRecord(medicalRecord);
    }
    @PutMapping("/medicalRecord")
    public void putMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
        medicalRecordRepository.putMedicalRecord(medicalRecord);
    }
    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) throws IOException {
        medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
    }
}
