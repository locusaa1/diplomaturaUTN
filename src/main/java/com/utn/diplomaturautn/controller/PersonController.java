package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.PersonDTO;
import com.utn.diplomaturautn.model.Person;
import com.utn.diplomaturautn.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    private CityController cityController;

    @Autowired
    public void PersonService(PersonService personService, CityController cityController) {

        this.personService = personService;
        this.cityController = cityController;
    }

    public ResponseEntity<List<Person>> response(List<Person> persons) {

        return ResponseEntity.
                status(persons.isEmpty() ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(persons);
    }

    public ResponseEntity<Person> response(Person person) {

        return ResponseEntity.
                status(person == null ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(person);
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getAll() {

        return this.response(this.personService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Person> addPerson(@RequestBody PersonDTO newPersonDTO) {

        return this.response(
                this.personService.addPerson(
                        Person.builder().
                                city(this.cityController.getById(newPersonDTO.getIdCity()).getBody()).
                                name(newPersonDTO.getName()).
                                lastName(newPersonDTO.getLastName()).
                                dni(newPersonDTO.getDni()).build()));
    }
}
