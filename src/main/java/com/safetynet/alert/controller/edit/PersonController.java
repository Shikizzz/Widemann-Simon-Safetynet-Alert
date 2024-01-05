package com.safetynet.alert.controller.edit;

import com.safetynet.alert.controller.CustomResponseEntity;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.edit.PersonEditer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
public class PersonController {

    private CustomResponseEntity customResponseEntity;

    private PersonEditer personEditer;
    @PostMapping("/person")
    public ResponseEntity<String> addPerson(@RequestBody Person person) throws FileNotFoundException {
        try {
            personEditer.postPerson(person);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Person added to Database successfully");
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundEditResponseEntity();
        }
    }

    @PutMapping("/person")
    public ResponseEntity<String> putPerson(@RequestBody Person person) throws FileNotFoundException {
        try {
            personEditer.putPerson(person);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Person modified in Database successfully");
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundEditResponseEntity();
        }
    }

    @DeleteMapping("/person")
    public ResponseEntity<String> deletePerson(@RequestParam String firstName,@RequestParam String lastName) throws FileNotFoundException {
        try {
            personEditer.deletePerson(firstName, lastName);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Person deleted in Database successfully");
        } catch (FileNotFoundException e) {
            return customResponseEntity.FileNotFoundEditResponseEntity();
        }
    }

}
