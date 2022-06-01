package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.PersonDTO;
import com.utn.diplomaturautn.model.Person;
import com.utn.diplomaturautn.service.CityService;
import com.utn.diplomaturautn.service.PersonService;
import com.utn.diplomaturautn.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {

    private final PersonService personService;

    private final CityService cityService;

    @Autowired
    public PersonController(PersonService personServiceImpl, CityService cityService) {

        this.personService = personServiceImpl;
        this.cityService = cityService;
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
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Person addPerson(@RequestBody @Valid PersonDTO newPersonDTO) {

        return this.personService.addPerson(
                        Person.builder().
                                city(this.cityService.getById(newPersonDTO.getIdCity())).
                                name(newPersonDTO.getName()).
                                lastName(newPersonDTO.getLastName()).
                                dni(newPersonDTO.getDni()).build());
    }
}
