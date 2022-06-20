package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.City;

import java.util.List;

public interface CityService {

    List<City> getAll();

    City getById(int cityId);
}
