package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Phone;
import com.utn.diplomaturautn.repositroy.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    private PhoneRepository phoneRepository;

    @Autowired
    public void PhoneRepository(PhoneRepository phoneRepository) {

        this.phoneRepository = phoneRepository;
    }


    public List<Phone> getAll() {

        return this.phoneRepository.findAll();
    }

    public Phone getById(int id) {

        return this.phoneRepository.findById(id).get();
    }

    public Phone addPhone(Phone phone) {

        return this.phoneRepository.save(phone);
    }

    public Phone getByNumber(String number) {

        Optional<Phone> phone = this.phoneRepository.findPhoneByNumber(number);

        if (phone.isEmpty()) {

            throw new ResourceNotFoundException("There is not a register with the specific number.");
        } else {

            return phone.get();
        }
    }
}
