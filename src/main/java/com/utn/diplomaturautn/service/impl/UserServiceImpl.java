package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.repositroy.ClientRepository;
import com.utn.diplomaturautn.repositroy.EmployeeRepository;
import com.utn.diplomaturautn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ClientRepository clientRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public UserServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository) {

        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    public UserDetails getByUsername(String username) {

        if (username.isEmpty()) {

            throw new InvalidBeanFieldsException("The username field is mandatory.");
        } else {

            Optional<UserDetails> user = this.clientRepository.findByUsernameEquals(username);

            if (user.isEmpty()) {

                user = this.employeeRepository.findByUsername(username);

                if (user.isEmpty()) {

                    throw new ResourceNotFoundException("There is not a register with the specific username.");
                }
            }
            return user.get();
        }
    }

    public UserDetails loadUserByUsername(String username) {

        return this.getByUsername(username);
    }
}
