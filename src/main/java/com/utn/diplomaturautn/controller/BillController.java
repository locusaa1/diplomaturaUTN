package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.model.City;
import com.utn.diplomaturautn.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {

        this.billService = billService;
    }

    public ResponseEntity<List<Bill>> response(List<Bill> bills) {

        return ResponseEntity.
                status(bills.isEmpty() ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(bills);
    }

    public ResponseEntity<Bill> response(Bill bill) {

        return ResponseEntity.
                status(bill == null ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(bill);
    }

    @GetMapping("/")
    public ResponseEntity<List<Bill>> getAll() {

        return this.response(this.billService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Bill> getById(@RequestParam("id") int id) {

        return this.response(this.billService.getById(id));
    }
}
