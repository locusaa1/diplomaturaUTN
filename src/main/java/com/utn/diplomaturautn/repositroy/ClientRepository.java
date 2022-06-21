package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends PersonRepository<Client> {

    Optional<UserDetails> findByUsernameEquals(String username);

    Optional<Client> findByPhoneEquals(Phone clientPhone);
}
