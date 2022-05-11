package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.repositroy.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {

        this.billRepository = billRepository;
    }

    public List<Bill> getAll() {

        return this.billRepository.findAll();
    }

    public Bill getById(int id) {

        return this.billRepository.findById(id).get();
    }
}
