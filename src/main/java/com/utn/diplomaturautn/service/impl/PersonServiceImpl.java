package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Person;
import com.utn.diplomaturautn.repositroy.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public Person getById(int id) {

        if (id <= 0) {

            throw new InvalidBeanFieldsException("The id must be higher than 0.");
        } else {

            Optional<Person> person = this.personRepository.findById(id);

            if (person.isEmpty()) {

                throw new ResourceNotFoundException("There is not a register with the specific id.");
            } else {

                return person.get();
            }
        }
    }

    public Person addPerson(Person newPerson) {

        return this.personRepository.save(newPerson);
    }
}
