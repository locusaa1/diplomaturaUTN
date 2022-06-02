package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Call;

import java.util.List;

public interface CallService {

    /**
     * Lists all calls from the repository.
     *
     * @return a list of calls.
     */
    public List<Call> getAll();

    /**
     * Searches for the specific call id into the repository
     *
     * @param id the id of the specific call.
     * @return the specific Cll object.
     */
    public Call getById(int id);

    /**
     * Adds a new call into the repository.
     *
     * @param newCall the Call object to be saved.
     * @return the Call object with his las form from the repository.
     */
    public Call addCall(Call newCall);
}
