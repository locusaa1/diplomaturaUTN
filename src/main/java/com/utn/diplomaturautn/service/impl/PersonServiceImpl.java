package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.model.Person;
import com.utn.diplomaturautn.repositroy.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl {

    private PersonRepository personRepository;

    @Autowired
    public void PersonRepository(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    public List<Person> getAll() {

        return this.personRepository.findAll();
    }

    public Person getById(int id){

        return this.personRepository.findById(id).get();
    }

    public Person addPerson(Person newPerson) {

        return this.personRepository.save(newPerson);
    }
}
