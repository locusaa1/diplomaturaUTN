package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.model.Phone;
import com.utn.diplomaturautn.model.Province;
import com.utn.diplomaturautn.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Specifies the URLS for the source in the class
@RequestMapping("/province") //Specifies the main URL for the class resource
public class ProvinceController {

    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {

        this.provinceService = provinceService;
    }

    public ResponseEntity<List<Province>> response(List<Province> provinces) {

        return ResponseEntity.
                status(provinces.isEmpty() ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(provinces);
    }

    public ResponseEntity<Province> response(Province province) {

        return ResponseEntity.
                status(province == null ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(province);
    }

    @GetMapping("/")
    public ResponseEntity<List<Province>> getAll() {

        return this.response(this.provinceService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Province> getById(@PathVariable("id") int id) {

        return this.response(this.provinceService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Province> addProvince(@RequestBody Province newProvince) {

        return this.response(this.provinceService.addProvince(newProvince));
    }
}
