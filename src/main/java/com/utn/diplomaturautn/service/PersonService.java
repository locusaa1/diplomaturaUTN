package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.dataTransferObject.PersonDTO;
import com.utn.diplomaturautn.model.Person;

import java.util.List;

public interface PersonService {

    /**
     * Lists all persons from the repository.
     *
     * @return a list of persons.
     */
    List<Person> getAll();

    /**
     * Searches for the specific person id into the repository.
     *
     * @param id the id of the specific person.
     * @return the specific Person object.
     */
    Person getById(int id);

    /**
     * Adds a new person into the repository.
     *
     * @param newPersonDTO the PersonDTO object to be saved.
     * @return the Person object with his last form from the repository.
     */
    Person addPerson(PersonDTO newPersonDTO);
}
