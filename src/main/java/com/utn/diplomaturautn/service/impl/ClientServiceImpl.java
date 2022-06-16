package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.exception.*;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.repositroy.ClientRepository;
import com.utn.diplomaturautn.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.utn.diplomaturautn.enumerated.ClientCondition.*;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAll() {

        List<Client> clientList = this.clientRepository.findAll();

        if (clientList.isEmpty()) {

            throw new NoContentException("There is no content in the database form this entity.");
        } else {

            return clientList;
        }
    }

    @Override
    public Client getById(int clientId) {

        if (clientId <= 0) {

            throw new InvalidBeanFieldsException("The id must be higher than 0.");
        } else {

            Optional<Client> client = this.clientRepository.findById(clientId);

            if (client.isEmpty()) {

                throw new ResourceNotFoundException("There is not a register with the specific id.");
            } else {

                return client.get();
            }
        }
    }

    @Override
    public Client addClient(Client newClient) {

        try {

            return this.clientRepository.save(newClient);
        } catch (Exception exception) {

            throw new ErrorSavingEntityException("Saving the entity failed. Nested exception message: " + exception.getMessage());
        }
    }

    @Override
    public Client modifyClient(Client clientNewData, int clientId) {

        Client clientFound = this.getById(clientId);

        if (!clientFound.equals(clientNewData)) {

            return this.clientRepository.save(clientFound.modifyUsingClient(clientNewData));

        } else {

            throw new NothingToModifyException("The new data matches all the client from the database, there is nothing to modify.");
        }
    }

    @Override
    public Client discontinueClient(int clientId) {

        Client clientFound = this.getById(clientId);

        if (clientFound.getCondition() == DISCONTINUED) {

            throw new NothingToModifyException("The client is already discontinued.");
        } else {

            clientFound.setCondition(DISCONTINUED);
        }
        return this.clientRepository.save(clientFound);
    }

    @Override
    public Client reactivateClient(int clientId) {

        Client clientFound = this.getById(clientId);

        if (clientFound.getCondition() == ACTIVE) {

            throw new NothingToModifyException("The client is already active.");
        } else {

            clientFound.setCondition(ACTIVE);
        }
        return this.clientRepository.save(clientFound);
    }

    @Override
    public Client deleteClient(int clientId) {

        Client clientFound = this.getById(clientId);

        if (clientFound.getCondition() == INACTIVE) {

            throw new NothingToModifyException("The client is already inactive.");
        } else {

            clientFound.setCondition(INACTIVE);
        }
        return this.clientRepository.save(clientFound);
    }
}
