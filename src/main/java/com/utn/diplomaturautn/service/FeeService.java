package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Fee;

import java.util.List;

public interface FeeService {

    /**
     * Lists all fees from the repository.
     *
     * @return a list of fees
     */
    List<Fee> getAll();

    /**
     * Searches for the specific fee id into the repository
     *
     * @param id the id of the specific fee.
     * @return the specific Fee object.
     */
    Fee getById(int id);

    /**
     * Adds a new fee into the repository
     *
     * @param newFee the Fee object to be saved.
     * @return the Fee object with his last form from the repository.
     */
    Fee addFee(Fee newFee);
}
