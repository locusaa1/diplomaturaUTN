package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.repositroy.BillRepository;
import com.utn.diplomaturautn.service.BillService;
import com.utn.diplomaturautn.utils.Utils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {

        this.billRepository = billRepository;
    }

    private List<Bill> checkEmptyListThrowsException(Optional<List<Bill>> billsList) {

        if (billsList.isEmpty()) {

            throw new NoContentException("There is not a register between the specifics dates");
        } else {

            return billsList.get();
        }
    }

    @Override
    public List<Bill> getAll() {

        return this.billRepository.findAll();
    }

    @Override
    public Bill getById(int id) {

        return this.billRepository.findById(id).get();
    }

    @Override
    public List<Bill> getByDateRangeAndUser(Timestamp from, Timestamp to, Client client) {

        Utils.compareDatesThrowingExceptions(from, to);

        Optional<List<Bill>> billsList = this.billRepository.findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqualAndClient(from, to, client);

        return this.checkEmptyListThrowsException(billsList);
    }

    public List<Bill> getByDateRange(Timestamp form, Timestamp to){

        
    }
}
