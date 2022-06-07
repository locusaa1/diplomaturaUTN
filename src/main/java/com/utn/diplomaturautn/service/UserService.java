package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.User;

import java.util.List;

public interface UserService {

    /**
     * Lists all users from the repository.
     *
     * @return a list of users.
     */
    List<User> getAll();

    /**
     * Searches for the specific user id into the repository.
     *
     * @param id the id of the specific user.
     * @return the specific User object.
     */
    User getById(int id);

    /**
     * Adds a new user into the repository.
     *
     * @param newUser the User object to be saved.
     * @return the User object with his las form from the repository.
     */
    User addUser(User newUser);
}
