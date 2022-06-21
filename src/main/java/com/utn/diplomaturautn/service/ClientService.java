package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.model.Phone;

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
     * @param employeeId the id of the specific client.
     * @return the specific Client object.
     */
    Client getById(int employeeId);

    Client getByPhone(Phone clientPhone);

    /**
     * Adds a new client into the repository.
     *
     * @param newClient The Client object to be saved.
     * @return the Client object with his last form from the repository.
     */
    Client addClient(Client newClient);

    /**
     * Modifies the fields that do not match the client into the repository.
     *
     * @param clientNewData the Client object with the new data.
     * @param clientId      the id from the client to be modified into the repository.
     * @return the Client object with his new form from the repository.
     */
    Client modifyClient(Client clientNewData, int clientId);

    /**
     * Sets the specific client condition (from the clientId) to "discontinued".
     *
     * @param clientId the client id to be discontinued.
     * @return the client info.
     */
    Client discontinueClient(int clientId);

    /**
     * Sets the specific client condition (from clientId) to "inactive".
     *
     * @param clientId the client id to be inactive.
     * @return the client info.
     */
    Client deleteClient(int clientId);

    /**
     * Sets the specific client condition (from clientId) to "active" again.
     *
     * @param clientId the client id to be activated.
     * @return the client info.
     */
    Client reactivateClient(int clientId);
}
