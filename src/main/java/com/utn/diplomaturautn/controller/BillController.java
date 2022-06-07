package com.utn.diplomaturautn.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.service.BillService;
import com.utn.diplomaturautn.service.impl.ClientServiceImpl;
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

    private final ClientServiceImpl clientServiceImpl;

    public BillController(BillService billService, ClientServiceImpl clientServiceImpl) {

        this.billService = billService;
        this.clientServiceImpl = clientServiceImpl;
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

    @GetMapping("/date&client")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Bill> getByDateRangeAndClient(@RequestParam("from") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String from,
                                              @RequestParam("to") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String to,
                                              @RequestParam("client") int clientId) {

        Timestamp dateFrom = Timestamp.valueOf(from + " 00:00:00");

        Timestamp dateTo = (to.equals(LocalDate.now().toString())) ?
                Timestamp.valueOf(to.concat(" " + LocalTime.now().toString())) :
                Timestamp.valueOf(to + " 23:59:59");

        Client client = this.clientServiceImpl.getById(clientId);

        return this.billService.getByDateRangeAndClient(dateFrom,
                dateTo,
                client);
    }

    @GetMapping("/date")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Bill> getByDateRange(@RequestParam("from") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String from,
                                     @RequestParam("to") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String to) {

        Timestamp dateFrom = Timestamp.valueOf(from + " 00:00:00");

        Timestamp dateTo = (to.equals(LocalDate.now().toString())) ?
                Timestamp.valueOf(to.concat(" " + LocalTime.now().toString())) :
                Timestamp.valueOf(to + " 23:59:59");

        return this.billService.getByDateRange(dateFrom, dateTo);
    }
}
