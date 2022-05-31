package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.ProvinceDTO;
import com.utn.diplomaturautn.model.Province;
import com.utn.diplomaturautn.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController //Specifies the URLS for the source in the class
@RequestMapping("api/province") //Specifies the main URL for the class resource
public class ProvinceController {

    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {

        this.provinceService = provinceService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Province> getAll() {

        return this.provinceService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Province getById(@PathVariable("id") int id) {

        return this.provinceService.getById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Province addProvince(@RequestBody @Valid ProvinceDTO newProvinceDTO) {

        return this.provinceService.addProvince(Province.builder().name(newProvinceDTO.getName()).build());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Province modifyProvince(@RequestBody @Valid ProvinceDTO patchProvinceDTO, @PathVariable("id") int provinceId) {

        return this.provinceService.modifyProvince(patchProvinceDTO, provinceId);
    }
}
