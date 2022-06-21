package com.utn.diplomaturautn.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.utn.diplomaturautn.dataTransferObject.BillResponseDTO;
import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.service.BillService;
import com.utn.diplomaturautn.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    public List<BillResponseDTO> getAll() {

        return Bill.fromBillListToResponseDTO(this.billService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BillResponseDTO getById(@PathVariable("id") int id) {

        return this.billService.getById(id).fromBillToResponseDTO();
    }

    @GetMapping("/date&client")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BillResponseDTO> getByDateRangeAndClient(@RequestParam("from") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String from,
                                                         @RequestParam("to") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String to,
                                                         @RequestParam("client") int clientId,
                                                         Authentication auth) {

        return Bill.fromBillListToResponseDTO(this.billService.getByDateRangeAndClient(from, to, this.clientService.getById(clientId), auth));
    }

    @GetMapping("/date")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BillResponseDTO> getByDateRange(@RequestParam("from") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String from,
                                                @RequestParam("to") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String to,
                                                Authentication auth) {

        return Bill.fromBillListToResponseDTO(this.billService.getByDateRange(from, to, auth));
    }
}
