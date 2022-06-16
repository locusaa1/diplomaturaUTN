package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.model.Client;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.sql.Timestamp;
import java.util.List;

public interface BillService {

    /**
     * Lists all bills from the repository.
     *
     * @return a list of bills.
     */
    List<Bill> getAll();

    /**
     * Searches for the specific bill id into the repository.
     *
     * @param id the id of the specific bill.
     * @return the specific Bill object.
     */
    Bill getById(int id);

    /**
     * Lists all bills from the repository that were made between the specific dates and matches the user phone
     *
     * @param from   the main date of the filter.
     * @param to     the end date of the filter.
     * @param client the id of the client into the database.
     * @param auth   the authentication instance of the request.
     * @return a list of bills filtered.
     */
    List<Bill> getByDateRangeAndClient(String from, String to, Client client, Authentication auth);

    /**
     * Lists all bills from the repository that were made between the specifics dates.
     *
     * @param form the main date of the filter.
     * @param to   the end date of the filter.
     * @param auth the authentication instance of the request.
     * @return a list of bills filtered.
     */
    List<Bill> getByDateRange(String form, String to, Authentication auth);
}
