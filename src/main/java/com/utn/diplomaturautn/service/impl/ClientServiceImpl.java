package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.repositroy.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements com.utn.diplomaturautn.service.ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public void ClientRepository(ClientRepository clientRepository){

        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(){

        return this.clientRepository.findAll();
    }

    public Client getById(int id){

        return this.clientRepository.findById(id).get();
    }

    public Client addClient(Client newClient){

        return this.clientRepository.save(newClient);
    }
}
