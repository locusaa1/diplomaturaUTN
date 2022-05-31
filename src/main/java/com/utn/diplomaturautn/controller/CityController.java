package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.CityDTO;
import com.utn.diplomaturautn.model.City;
import com.utn.diplomaturautn.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;
    private final ProvinceController provinceController;

    @Autowired
    public CityController(CityService cityService, ProvinceController provinceController) {

        this.cityService = cityService;
        this.provinceController = provinceController;
    }

    public ResponseEntity<List<City>> response(List<City> cities) {

        return ResponseEntity.
                status(cities.isEmpty() ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(cities);
    }

    public ResponseEntity<City> response(City city) {

        return ResponseEntity.
                status(city == null ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(city);
    }

    @GetMapping("/")
    public ResponseEntity<List<City>> getAll() {

        return this.response(this.cityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@RequestParam("id") int id) {

        return this.response(this.cityService.getById(id));
    }


    @PostMapping("/")
    public ResponseEntity<City> addCity(@RequestBody CityDTO cityDTO) {

        return this.response(this.cityService.addCity(
                City.builder().
                        province(this.provinceController.getById(cityDTO.getIdProvince())).
                        name(cityDTO.getName()).
                        areaCode(cityDTO.getAreaCode()).
                        postalCode(cityDTO.getPostalCode()).build()));
    }
}
