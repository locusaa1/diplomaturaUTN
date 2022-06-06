package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Bill;
import com.utn.diplomaturautn.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    Optional<List<Bill>> findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqualAndClient(Timestamp startDate, Timestamp endDate, Client client);

    Optional<List<Bill>> findByGeneratedDateGreaterThanEqualAndGeneratedDateIsLessThanEqual(Timestamp from, Timestamp to);
}
