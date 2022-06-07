package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
