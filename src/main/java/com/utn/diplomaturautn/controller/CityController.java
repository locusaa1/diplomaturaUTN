package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.CityDTO;
import com.utn.diplomaturautn.model.City;
import com.utn.diplomaturautn.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private CityService cityService;
    private ProvinceController provinceController;

    @Autowired
    public void CityService(CityService cityService, ProvinceController provinceController) {

        this.cityService = cityService;
        this.provinceController = provinceController;
    }

    @GetMapping("/")
    public List<City> getAll() {

        List<City> asd = this.cityService.getAll();
        return this.cityService.getAll();
    }

    @PostMapping("/")
    public void addCity(@RequestBody CityDTO cityDTO) {

        this.cityService.addCity(
                City.builder().
                        province(this.provinceController.getById(cityDTO.getIdProvince())).
                        name(cityDTO.getName()).
                        areaCode(cityDTO.getAreaCode()).
                        postalCode(cityDTO.getPostalCode()).build());
    }
}
