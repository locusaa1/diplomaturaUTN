package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends PersonRepository<Employee> {

    Optional<UserDetails> findByUsername(String username);
}
