package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Fee;
import com.utn.diplomaturautn.repositroy.FeeRepository;
import com.utn.diplomaturautn.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;

    @Autowired
    public FeeServiceImpl(FeeRepository feeRepository) {

        this.feeRepository = feeRepository;
    }

    @Override
    public List<Fee> getAll() {

        List<Fee> feesList = this.feeRepository.findAll();

        if (feesList.isEmpty()) {

            throw new NoContentException("There is no content in database from this entity.");
        } else {

            return feesList;
        }
    }

    @Override
    public Fee getById(int id) {

        if (id <= 0) {

            throw new InvalidBeanFieldsException("The id must be higher than 0");
        } else {

            Optional<Fee> fee = this.feeRepository.findById(id);

            if (fee.isEmpty()) {

                throw new ResourceNotFoundException("There is not a register with the specific id.");
            } else {

                return fee.get();
            }
        }
    }
}
