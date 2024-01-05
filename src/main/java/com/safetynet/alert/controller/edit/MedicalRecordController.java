package com.safetynet.alert.controller.edit;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.edit.MedicalRecordEditer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;

@RestController
@AllArgsConstructor
public class MedicalRecordController {

    private CustomResponseEntity customResponseEntity;

    private MedicalRecordEditer medicalRecordEditer;
    @PostMapping("/medicalRecord")
    public ResponseEntity<String> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws FileNotFoundException {
        try {
            medicalRecordEditer.postMedicalRecord(medicalRecord);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Medical Record added to Database successfully");
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundEditResponseEntity();
        }
    }
    @PutMapping("/medicalRecord")
    public ResponseEntity<String> putMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws FileNotFoundException {
        try {
            medicalRecordEditer.putMedicalRecord(medicalRecord);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Medical Record modified to Database successfully");
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundEditResponseEntity();
        }
    }
    @DeleteMapping("/medicalRecord")
    public ResponseEntity<String> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) throws FileNotFoundException {
        try {
            medicalRecordEditer.deleteMedicalRecord(firstName, lastName);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Medical Record deleted to Database successfully");
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundEditResponseEntity();
        }
    }
}
