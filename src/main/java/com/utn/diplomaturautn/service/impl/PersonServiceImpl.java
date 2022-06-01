package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.exception.NoContentException;
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

        List<Person> personList = this.personRepository.findAll();

        if (personList.isEmpty()) {

            throw new NoContentException("There is no content in the data base from this entity");
        } else {

            return personList;
        }
    }

    public Person getById(int id){

        return this.personRepository.findById(id).get();
    }

    public Person addPerson(Person newPerson) {

        return this.personRepository.save(newPerson);
    }
}
