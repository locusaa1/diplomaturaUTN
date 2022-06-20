package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.dataTransferObject.ProvinceDTO;
import com.utn.diplomaturautn.model.Province;

import java.util.List;

public interface ProvinceService {

    /**
     * Lists all provinces from the repository.
     *
     * @return a list of provinces.
     */
    List<Province> getAll();

    /**
     * Searches for the specific province id into the repository.
     *
     * @param id the id of the specific province.
     * @return the specific Province object.
     */
    Province getById(int id);

    /**
     * Adds a new province into the repository.
     *
     * @param newProvince the Province object to be saved.
     * @return the Province object with his last form from the repository.
     */
    Province addProvince(Province newProvince);
}
