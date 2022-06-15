package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.CityDTO;
import com.utn.diplomaturautn.model.City;
import com.utn.diplomaturautn.service.CityService;
import com.utn.diplomaturautn.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;
    private final ProvinceService provinceService;

    @Autowired
    public CityController(CityService cityService, ProvinceService provinceService) {

        this.cityService = cityService;
        this.provinceService = provinceService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<City> getAll() {

        return this.cityService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public City getById(@PathVariable("id") int id) {

        return this.cityService.getById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public City addCity(@RequestBody CityDTO cityDTO) {

        return this.cityService.addCity(
                City.builder().
                        province(this.provinceService.getById(cityDTO.getIdProvince())).
                        name(cityDTO.getName()).
                        areaCode(cityDTO.getAreaCode()).
                        postalCode(cityDTO.getPostalCode()).build());
    }
}
