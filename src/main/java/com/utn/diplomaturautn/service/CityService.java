package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.City;
import com.utn.diplomaturautn.repositroy.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private CityRepository cityRepository;

    @Autowired
    public void CityRepository(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void addCity(City city) {
        this.cityRepository.save(city);
    }

    public List<City> getAll() {
        return this.cityRepository.findAll();
    }
}
