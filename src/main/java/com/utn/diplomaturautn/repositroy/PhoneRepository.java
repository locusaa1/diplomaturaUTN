package com.utn.diplomaturautn.repositroy;

import com.utn.diplomaturautn.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    public Optional<Phone> findPhoneByNumber(String number);
}
