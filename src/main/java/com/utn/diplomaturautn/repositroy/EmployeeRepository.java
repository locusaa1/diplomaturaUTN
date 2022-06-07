package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PersonRepository {

}
