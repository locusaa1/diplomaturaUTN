package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.City;

import java.util.List;

public interface CityService {

    City addCity(City city);

    City getById(int id);

    List<City> getAll();
}
