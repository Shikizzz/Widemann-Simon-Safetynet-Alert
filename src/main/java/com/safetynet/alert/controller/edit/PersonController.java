package com.safetynet.alert.controller.edit;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.edit.PersonEditer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
public class PersonController {
    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    private CustomResponseEntity customResponseEntity;

    private PersonEditer personEditer;

    @PostMapping("/person")
    public ResponseEntity<String> addPerson(@RequestBody Person person) throws FileNotFoundException {
        try {
            if (personEditer.postPerson(person)) {
                logger.info("Endpoint POST/person used successfully");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Person added to Database successfully");
            } else {
                logger.info("Endpoint POST/person used, but the Person was already in DataBase ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Person already in DataBase");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint POST/person used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }

    @PutMapping("/person")
    public ResponseEntity<String> putPerson(@RequestBody Person person) throws FileNotFoundException {
        try {
            if (personEditer.putPerson(person)) {
                logger.info("Endpoint PUT/person used successfully");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Person modified in Database successfully");
            } else {
                logger.info("Endpoint PUT/person used, but the Person was not found in DataBase ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Person not found in DataBase");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint PUT/person used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }

    @DeleteMapping("/person")
    public ResponseEntity<String> deletePerson(@RequestParam String firstName, @RequestParam String lastName) throws FileNotFoundException {
        try {
            if (personEditer.deletePerson(firstName, lastName)) {
                logger.info("Endpoint DELETE/person used successfully");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Person deleted in Database successfully");
            } else {
                logger.info("Endpoint DELETE/person used, but the Person was not found in DataBase ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Person not found in DataBase");
            }
        } catch (FileNotFoundException e) {
            logger.error("Endpoint DELETE/person used, but DataBase File not found");
            return customResponseEntity.FileNotFoundResponseEntity();
        }
    }
}
