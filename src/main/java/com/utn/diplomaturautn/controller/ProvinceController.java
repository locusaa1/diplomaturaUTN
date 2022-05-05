package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.model.Province;
import com.utn.diplomaturautn.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Specifies the URLS for the source in the class
@RequestMapping("/province") //Specifies the main URL for the class resource
public class ProvinceController {

    private ProvinceService provinceService;

    @Autowired
    public void ProvinceService(ProvinceService provinceService) {

        this.provinceService = provinceService;
    }

    @GetMapping("/")
    public List<Province> getAll(){

        return this.provinceService.getAll();
    }

    @GetMapping("{id}")
    public Province getById(@PathVariable("id")int id){

        return this.provinceService.getById(id);
    }

    @PostMapping("/")
    public void addProvince(@RequestBody Province newProvince) {

        this.provinceService.addProvince(newProvince);
    }
}
