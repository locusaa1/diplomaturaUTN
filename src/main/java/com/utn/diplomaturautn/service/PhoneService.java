package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneService {

    List<Phone> getAll();

    Phone getById(int id);

    Phone addPhone(String areaCode, String phoneNumber);

    Phone getByNumber(String number);

    void deletePhoneByNumber(String number);
}
