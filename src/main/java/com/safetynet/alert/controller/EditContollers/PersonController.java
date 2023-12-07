package com.safetynet.alert.controller.EditContollers;

import com.safetynet.alert.model.DAO.Person;
import com.safetynet.alert.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RestController
public class PersonController {

    private PersonRepository personRepository;
    @PostMapping("/person")
    public void addPerson(@RequestBody Person person) throws IOException {
        personRepository.postPerson(person);
    }

    @PutMapping("/person")
    public void putPerson(@RequestBody Person person) throws IOException {
        personRepository.putPerson(person);
    }

    @DeleteMapping("/person")
    public void deletePerson(@RequestParam String firstName, String lastName) throws IOException {
        personRepository.deletePerson(firstName, lastName);
    }

}
