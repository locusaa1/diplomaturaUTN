package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.model.User;
import com.utn.diplomaturautn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    public ResponseEntity<List<User>> response(List<User> users) {

        return ResponseEntity.
                status(users.isEmpty() ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(users);
    }

    public ResponseEntity<User> response(User user) {

        return ResponseEntity.
                status(user == null ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {

        return this.response(this.userService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@RequestParam("id") int id) {

        return this.response(this.userService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<User> addUser(User newUser) {

        return this.response(this.userService.addUser(newUser));
    }
}