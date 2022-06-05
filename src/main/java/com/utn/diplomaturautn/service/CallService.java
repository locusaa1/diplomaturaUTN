package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.model.Phone;

import java.sql.Date;
import java.sql.Timestamp;
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

    /**
     * Lists all calls from the repository that were made between the specifics dates and matches the user phone.
     *
     * @param from        the main date of the filter.
     * @param to          the end date of the filter.
     * @param originPhone the Phone of the user.
     * @return a list of calls filtered.
     */
    public List<Call> getByDateRangeAndUser(Timestamp from, Timestamp to, Phone originPhone);

    /**
     * List all calls from the repository that were made between the specifics dates.
     *
     * @param from the main date of the filter.
     * @param to   the end date of the filter.
     * @return a list of calls filtered.
     */
    public List<Call> getByDateRange(Timestamp from, Timestamp to);
}
