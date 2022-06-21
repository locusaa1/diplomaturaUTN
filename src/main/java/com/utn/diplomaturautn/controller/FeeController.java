package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.FeeDTO;
import com.utn.diplomaturautn.dataTransferObject.FeeResponseDTO;
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

    public FeeController(FeeService feeService) {

        this.feeService = feeService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<FeeResponseDTO> getAll() {

        return Fee.fromFeeListToResponseDTO(this.feeService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FeeResponseDTO getById(@PathVariable("id") int id) {

        return this.feeService.getById(id).fromFeeToResponseDTO();
    }
}
