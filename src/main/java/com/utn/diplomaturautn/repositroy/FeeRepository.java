package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee,Integer> {
}
