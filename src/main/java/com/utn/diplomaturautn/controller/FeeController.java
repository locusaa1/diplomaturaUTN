package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.FeeDTO;
import com.utn.diplomaturautn.model.City;
import com.utn.diplomaturautn.model.Employee;
import com.utn.diplomaturautn.model.Fee;
import com.utn.diplomaturautn.service.FeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
public class FeeController {

    private final FeeService feeService;

    private final CityController cityController;

    public FeeController(FeeService feeService, CityController cityController) {

        this.feeService = feeService;
        this.cityController = cityController;
    }

    public ResponseEntity<List<Fee>> response(List<Fee> fees) {

        return ResponseEntity.
                status(fees.isEmpty() ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(fees);
    }

    public ResponseEntity<Fee> response(Fee fee) {

        return ResponseEntity.
                status(fee == null ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(fee);
    }

    @GetMapping("/")
    public ResponseEntity<List<Fee>> getAll() {

        return this.response(this.feeService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Fee> getById(@RequestParam("id") int id) {

        return this.response(this.feeService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Fee> addFee(@RequestBody FeeDTO newFeeDTO) {

        return this.response(
                this.feeService.addFee(Fee.builder().
                        originCity(this.cityController.getById(newFeeDTO.getIdOriginCity()).getBody()).
                        destinationCity(this.cityController.getById(newFeeDTO.getIdDestinationCity()).getBody()).
                        cost(newFeeDTO.getCost()).
                        startTime(newFeeDTO.getStartTime()).
                        endTime(newFeeDTO.getEndTime()).build()));
    }
}
