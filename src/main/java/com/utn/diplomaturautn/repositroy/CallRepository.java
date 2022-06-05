package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.model.Phone;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public interface CallRepository extends CustomRepository<Call, Integer> {
    Optional<List<Call>> findByStartDateGreaterThanEqualAndStartDateIsLessThanEqualAndOriginPhone(Timestamp startDate, Timestamp endDate, Phone originPhone);

    Optional<List<Call>> findByStartDateGreaterThanEqualAndStartDateIsLessThanEqual(Timestamp startDate, Timestamp endDate);
}
