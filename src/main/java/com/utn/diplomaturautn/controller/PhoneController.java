package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.model.Phone;
import com.utn.diplomaturautn.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    private PhoneService phoneService;

    @Autowired
    public void phoneService(PhoneService phoneService) {

        this.phoneService = phoneService;
    }

    public ResponseEntity<List<Phone>> response(List<Phone> phones) {

        return ResponseEntity.
                status(phones.isEmpty() ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(phones);
    }

    public ResponseEntity<Phone> response(Phone phone) {

        return ResponseEntity.
                status(phone == null ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(phone);
    }

    @GetMapping("/")
    public ResponseEntity<List<Phone>> getAll() {

        return this.response(this.phoneService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Phone> getById(@RequestParam("id") int id) {

        return this.response(this.phoneService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Phone> addPhone(@RequestBody String newPhone) {

        return this.response(this.phoneService.addPhone(Phone.builder().number(newPhone).build()));
    }
}
