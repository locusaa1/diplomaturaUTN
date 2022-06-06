package com.utn.diplomaturautn.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.service.BillService;
import com.utn.diplomaturautn.service.ClientService;
import com.utn.diplomaturautn.service.impl.BillServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/bill")
public class BillController {

    private final BillService billService;

    private final ClientService clientService;

    public BillController(BillService billService, ClientService clientService) {

        this.billService = billService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Bill> getAll() {

        return this.billService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Bill getById(@PathVariable("id") int id) {

        return this.billService.getById(id);
    }

    @GetMapping("date&client")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Bill> getByDateRangeAndUser(@RequestParam("from") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String from,
                                            @RequestParam("to") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String to,
                                            @RequestParam("client") int clientId) {

        Timestamp dateFrom = Timestamp.valueOf(from + " 00:00:00");

        Timestamp dateTo = (to.equals(LocalDate.now().toString())) ?
                Timestamp.valueOf(to.concat(" " + LocalTime.now().toString())) :
                Timestamp.valueOf(to + " 23:59:59");

        Client client = this.clientService.getById(clientId);

        return this.billService.getByDateRangeAndUser(dateFrom,
                dateTo,
                client);
    }
}
