package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.FeeDTO;
import com.utn.diplomaturautn.model.Fee;
import com.utn.diplomaturautn.service.CityService;
import com.utn.diplomaturautn.service.FeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fee")
public class FeeController {

    private final FeeService feeService;

    private final CityService cityService;

    public FeeController(FeeService feeService, CityService cityService) {

        this.feeService = feeService;
        this.cityService = cityService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Fee> getAll() {

        return this.feeService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Fee getById(@PathVariable("id") int id) {

        return this.feeService.getById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Fee addFee(@RequestBody FeeDTO newFeeDTO) {

        return this.feeService.addFee(Fee.builder().
                originCity(this.cityService.getById(newFeeDTO.getIdOriginCity())).
                destinationCity(this.cityService.getById(newFeeDTO.getIdDestinationCity())).
                cost(newFeeDTO.getCost()).
                startTime(newFeeDTO.getStartTime()).
                endTime(newFeeDTO.getEndTime()).build());
    }
}
