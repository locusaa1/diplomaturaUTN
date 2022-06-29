package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.exception.*;
import com.utn.diplomaturautn.model.Phone;
import com.utn.diplomaturautn.repositroy.PhoneRepository;
import com.utn.diplomaturautn.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        List<Phone> phoneList = this.phoneRepository.findAll();

        if (!phoneList.isEmpty()) {

            return phoneList;
        } else {

            throw new NoContentException("There is no content in the database from this entity");
        }
    }

    public Phone getById(int id) {

        if (id > 0) {

            Optional<Phone> phoneFound = this.phoneRepository.findById(id);
            if (phoneFound.isPresent()) {

                return phoneFound.get();
            } else {

                throw new ResourceNotFoundException("There is not a register with the specific id");
            }
        } else {

            throw new InvalidBeanFieldsException("The id must be higher than 0");
        }
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

    @Transactional
    public void deletePhoneByNumber(String number) {

        try {

            this.phoneRepository.deletePhoneByNumber(number);
        } catch (Exception exception) {

            throw new InvalidPhoneException("Something went wrong trying to delete the specific phone number: " + exception.getCause().toString());
        }

    }
}
