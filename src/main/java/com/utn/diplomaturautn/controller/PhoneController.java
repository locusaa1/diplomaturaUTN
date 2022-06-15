package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.model.Phone;
import com.utn.diplomaturautn.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Phone> getAll() {

        return this.phoneService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Phone getById(@PathVariable("id") int id) {

        return this.phoneService.getById(id);
    }

    @GetMapping("/{number}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Phone getByNumber(@PathVariable("number") String number) {

        return this.phoneService.getByNumber(number);
    }
}
