package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.exception.ErrorSavingEntityException;
import com.utn.diplomaturautn.exception.InvalidPhoneException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Phone;
import com.utn.diplomaturautn.repositroy.PhoneRepository;
import com.utn.diplomaturautn.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository) {

        this.phoneRepository = phoneRepository;
    }


    public List<Phone> getAll() {

        return this.phoneRepository.findAll();
    }

    public Phone getById(int id) {

        return this.phoneRepository.findById(id).get();
    }

    public Phone addPhone(String areaCode, String phoneNumber) {

        if (phoneNumber.startsWith(areaCode)) {

            Phone newPhone = new Phone(0, phoneNumber);

            try {

                return this.phoneRepository.save(newPhone);
            } catch (Exception exception) {

                throw new ErrorSavingEntityException("Saving the entity failed. Nested exception message: " + exception.getMessage());
            }


        } else {

            throw new InvalidPhoneException("The user phone number does not match his city area code.");
        }
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
