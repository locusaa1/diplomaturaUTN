package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {

        this.billService = billService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Bill> getAll() {

        return this.billService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public Bill getById(@PathVariable("id") int id) {

        return this.billService.getById(id);
    }
}
