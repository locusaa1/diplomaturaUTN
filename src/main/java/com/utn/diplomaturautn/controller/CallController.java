package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/call")
public class CallController {

    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {

        this.callService = callService;
    }

    public ResponseEntity<List<Call>> response(List<Call> calls) {

        return ResponseEntity.
                status(calls.isEmpty() ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(calls);
    }

    public ResponseEntity<Call> response(Call call) {

        return ResponseEntity.
                status(call == null ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(call);
    }

    @GetMapping("/")
    public ResponseEntity<List<Call>> getAll() {

        return this.response(this.callService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Call> getById(@PathParam("id") int id) {

        return this.response(this.callService.getById(id));
    }

    /*@PostMapping("/")
    public ResponseEntity<Call> addCall(@RequestBody CallDTO newCallDTO) {

        return this.response(this.callService.
                addCall(Call.builder().
                        ))
    }*/
}
