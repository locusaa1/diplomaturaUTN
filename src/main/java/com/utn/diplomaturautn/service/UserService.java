package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.User;
import com.utn.diplomaturautn.repositroy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<User> getAll() {

        return this.userRepository.findAll();
    }

    public User getById(int id) {

        return this.userRepository.findById(id).get();
    }

    public User addUser(User newUser) {

        return this.userRepository.save(newUser);
    }


}
