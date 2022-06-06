package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.FeeDTO;
import com.utn.diplomaturautn.model.Fee;
import com.utn.diplomaturautn.service.FeeService;
import com.utn.diplomaturautn.service.impl.FeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fee")
public class FeeController {

    private final FeeService feeService;

    private final CityController cityController;

    public FeeController(FeeService feeService, CityController cityController) {

        this.feeService = feeService;
        this.cityController = cityController;
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
                originCity(this.cityController.getById(newFeeDTO.getIdOriginCity()).getBody()).
                destinationCity(this.cityController.getById(newFeeDTO.getIdDestinationCity()).getBody()).
                cost(newFeeDTO.getCost()).
                startTime(newFeeDTO.getStartTime()).
                endTime(newFeeDTO.getEndTime()).build());
    }
}
