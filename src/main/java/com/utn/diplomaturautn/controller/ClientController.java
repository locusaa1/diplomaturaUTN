package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.ClientDTO;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final PersonController personController;
    private final PhoneController phoneController;

    @Autowired
    public ClientController (ClientService clientService, PersonController personController, PhoneController phoneController){

        this.clientService = clientService;
        this.personController = personController;
        this.phoneController = phoneController;
    }

    public ResponseEntity<List<Client>> response(List<Client> clients) {

        return ResponseEntity.
                status(clients.isEmpty() ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(clients);
    }

    public ResponseEntity<Client> response(Client client) {

        return ResponseEntity.
                status(client == null ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.OK).
                body(client);
    }

     @GetMapping("/")
    public ResponseEntity<List<Client>> getAll(){

        return this.response(this.clientService.getAll());
     }

     @GetMapping("{id}")
    public ResponseEntity<Client> getById(@RequestParam("id") int id){

        return this.response(this.clientService.getById(id));
     }

    /* @PostMapping("/")
    public ResponseEntity<Client> addClient(@RequestBody ClientDTO newClientDTO){

        return this.response(this.clientService.
                addClient(Client.builder().
                        person(this.personController.getById(newClientDTO.getIdPerson()).getBody()).
                        phone(this.phoneController.getById(newClientDTO.getIdPhone()).getBody()).build()));
     }*/
}
