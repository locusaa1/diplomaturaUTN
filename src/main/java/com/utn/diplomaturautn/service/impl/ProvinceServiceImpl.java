package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.dataTransferObject.ProvinceDTO;
import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.exception.NothingToModifyException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.Province;
import com.utn.diplomaturautn.repositroy.ProvinceRepository;
import com.utn.diplomaturautn.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private ProvinceRepository provinceRepository;

    @Autowired
    public void ProvinceRepository(ProvinceRepository provinceRepository) {

        this.provinceRepository = provinceRepository;
    }

    public List<Province> getAll() {

        List<Province> provinceList = this.provinceRepository.findAll();

        if (provinceList.isEmpty()) {

            throw new NoContentException("There is no content in the database from this entity.");
        } else {

            return provinceList;
        }
    }

    public Province getById(int id) {

        if (id <= 0) {

            throw new InvalidBeanFieldsException("The id must be higher than 0.");
        } else {

            Optional<Province> province = this.provinceRepository.findById(id);

            if (province.isEmpty()) {

                throw new ResourceNotFoundException("There is not a register with the specific id.");
            } else {

                return province.get();
            }
        }
    }

    public Province addProvince(Province newProvince) {

        try {

            return this.provinceRepository.save(newProvince);
        } catch (Exception exception) {

            throw exception;
        }
    }

    public Province modifyProvince(ProvinceDTO patchProvince, int provinceId) {

        if (provinceId <= 0) {

            throw new InvalidBeanFieldsException("The id must be higher than 0.");
        } else {

            Optional<Province> storedProvince = this.provinceRepository.findById(provinceId);

            if (storedProvince.isEmpty()) {

                throw new ResourceNotFoundException("There is not a register with the specific id.");
            } else {

                Province province = storedProvince.get();

                if (province.getName().equals(patchProvince.getName())) {

                    throw new NothingToModifyException("The stored register can not be updated with the same attributes.");
                } else {

                    province.setName(patchProvince.getName());
                    return this.provinceRepository.save(province);
                }
            }
        }
    }
}
