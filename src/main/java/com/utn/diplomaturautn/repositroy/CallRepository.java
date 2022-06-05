package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Call;
import com.utn.diplomaturautn.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public interface CallRepository extends CustomRepository<Call, Integer> {
    public Optional<List<Call>> findByStartDateGreaterThanEqualAndStartDateIsLessThanEqualAndOriginPhone(Timestamp startDate, Timestamp endDate, Phone originPhone);
}
