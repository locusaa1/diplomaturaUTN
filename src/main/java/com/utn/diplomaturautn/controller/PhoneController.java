package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.model.Phone;
import com.utn.diplomaturautn.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/phone")
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {

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

    @GetMapping
    public ResponseEntity<Phone> getById(@RequestParam("id") int id) {

        return this.response(this.phoneService.getById(id));
    }

    @GetMapping("/{number}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Phone getByNumber(@PathVariable("number") String number) {

        return this.phoneService.getByNumber(number);
    }

    @PostMapping("/")
    public ResponseEntity<Phone> addPhone(@RequestBody Phone phone) {

        return this.response(this.phoneService.addPhone(phone));
    }
}
