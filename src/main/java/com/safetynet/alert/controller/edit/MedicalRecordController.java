package com.safetynet.alert.controller.edit;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.edit.MedicalRecordEditer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@AllArgsConstructor
public class MedicalRecordController {
    private static Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    private CustomResponseEntity customResponseEntity;

    private MedicalRecordEditer medicalRecordEditer;

    @PostMapping("/medicalRecord")
    public ResponseEntity<String> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws FileNotFoundException {
        try {
            if (medicalRecordEditer.postMedicalRecord(medicalRecord)) {
                logger.info("Endpoint POST/medicalRecord used successfully");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Medical Record added to Database successfully");
            } else {
                logger.info("Endpoint POST/medicalRecord used, but the MedicalRecord was already in DataBase ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("MedicalRecord already in DataBase");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint POST/medicalRecord used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }

    @PutMapping("/medicalRecord")
    public ResponseEntity<String> putMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws FileNotFoundException {
        try {
            if (medicalRecordEditer.putMedicalRecord(medicalRecord)) {
                logger.info("Endpoint PUT/medicalRecord used successfully");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Medical Record modified to Database successfully");
            } else {
                logger.info("Endpoint PUT/medicalRecord used, but the MedicalRecord was not found in DataBase ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("MedicalRecord not found in DataBase");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint PUT/medicalRecord used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }

    @DeleteMapping("/medicalRecord")
    public ResponseEntity<String> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) throws FileNotFoundException {
        try {
            if (medicalRecordEditer.deleteMedicalRecord(firstName, lastName)) {
                logger.info("Endpoint DELETE/medicalRecord used successfully");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Medical Record deleted to Database successfully");
            } else {
                logger.info("Endpoint DELETE/medicalRecord used, but the MedicalRecord was not found in DataBase ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("MedicalRecord not found in DataBase");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint PUT/medicalRecord used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}
