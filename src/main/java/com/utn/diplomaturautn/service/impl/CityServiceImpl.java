package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.exception.InvalidBeanFieldsException;
import com.utn.diplomaturautn.exception.NoContentException;
import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.model.City;
import com.utn.diplomaturautn.repositroy.CityRepository;
import com.utn.diplomaturautn.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public void CityRepository(CityRepository cityRepository) {

        this.cityRepository = cityRepository;
    }

    public List<City> getAll() {

        List<City> cityList = this.cityRepository.findAll();

        if (!cityList.isEmpty()) {

            return cityList;
        } else {

            throw new NoContentException("There is no content in the database from this entity");
        }
    }

    public City getById(int cityId) {

        if (cityId > 0) {

            Optional<City> cityFound = this.cityRepository.findById(cityId);
            if (cityFound.isPresent()) {

                return cityFound.get();
            } else {

                throw new ResourceNotFoundException("There is not a register with the specific id.");
            }
        } else {

            throw new InvalidBeanFieldsException("The id must be higher than 0.");
        }
    }
}
