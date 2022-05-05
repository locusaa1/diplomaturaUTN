package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

}
