package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Fee;
import com.utn.diplomaturautn.repositroy.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {

    private final FeeRepository feeRepository;

    @Autowired
    public FeeService(FeeRepository feeRepository) {

        this.feeRepository = feeRepository;
    }

    public List<Fee> getAll() {

        return this.feeRepository.findAll();
    }

    public Fee getById(int id) {

        return this.feeRepository.findById(id).get();
    }

    public Fee addFee(Fee newFee) {

        return this.feeRepository.save(newFee);
    }
}
