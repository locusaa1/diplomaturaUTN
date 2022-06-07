package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Client;

import java.util.List;

public interface ClientService {

    /**
     * Lists all clients from the repository.
     *
     * @return a list of clients.
     */
    List<Client> getAll();

    /**
     * Searches for the specific client id into the repository.
     *
     * @param id the id of the specific client.
     * @return the specific Client object.
     */
    Client getById(int id);

    /**
     * Adds a new client into the repository.
     *
     * @param newClient The Client object to be saved.
     * @return the Client object with his last form from the repository.
     */
    Client addClient(Client newClient);
}
