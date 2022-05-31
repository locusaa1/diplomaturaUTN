package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;

@Repository
public interface CallRepository extends JpaRepository<Call, Integer> {

}
