package com.utn.diplomaturautn.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.utn.diplomaturautn.dataTransferObject.CallDTO;
import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.service.CallService;
import com.utn.diplomaturautn.service.ClientService;
import com.utn.diplomaturautn.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/call")
public class CallController {

    private final CallService callService;

    private final PhoneService phoneService;

    private final ClientService clientService;

    @Autowired
    public CallController(CallService callService, PhoneService phoneService, ClientService clientService) {

        this.callService = callService;
        this.phoneService = phoneService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Call> getAll() {

        return this.callService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Call getById(@PathVariable("id") int id) {

        return this.callService.getById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Call addCall(@RequestBody @Valid CallDTO newCallDTO) {

        return this.callService.addCall(
                Call.builder().
                        originPhone(this.phoneService.getByNumber(newCallDTO.getOriginPhone())).
                        destinationPhone(this.phoneService.getByNumber(newCallDTO.getDestinationPhone())).
                        startDate(Timestamp.valueOf(newCallDTO.getDateTime())).
                        duration(newCallDTO.getDuration()).build());
    }

    @GetMapping("/date&client")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Call> getByDateRangeAndUser(@RequestParam("from") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String from,
                                            @RequestParam("to") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String to,
                                            @RequestParam("clientId") int clientId,
                                            Authentication auth) {

        return this.callService.getByDateRangeAndUser(from, to, this.clientService.getById(clientId).getPhone(), auth);
    }

    @GetMapping("/date")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Call> getByDateRange(@RequestParam("from") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String from,
                                     @RequestParam("to") @JsonFormat(pattern = "yyyy-MM-dd") @Valid String to,
                                     Authentication auth) {

        return this.callService.getByDateRange(from, to, auth);
    }
}
