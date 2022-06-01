package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.PersonDTO;
import com.utn.diplomaturautn.model.Person;
import com.utn.diplomaturautn.service.PersonService;
import com.utn.diplomaturautn.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController implements PersonService {

    private final PersonService personService;

    private final CityController cityController;

    @Autowired
    public PersonController(PersonService personServiceImpl, CityController cityController) {

        this.personService = personServiceImpl;
        this.cityController = cityController;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Person> getAll() {

        return this.personService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Person getById(@PathVariable("id") int id){

        return this.personService.getById(id);
    }

    @PostMapping("/")
    public Person addPerson(@RequestBody PersonDTO newPersonDTO) {

        return this.response(
                this.personServiceImpl.addPerson(
                        Person.builder().
                                city(this.cityController.getById(newPersonDTO.getIdCity()).getBody()).
                                name(newPersonDTO.getName()).
                                lastName(newPersonDTO.getLastName()).
                                dni(newPersonDTO.getDni()).build()));
    }
}
