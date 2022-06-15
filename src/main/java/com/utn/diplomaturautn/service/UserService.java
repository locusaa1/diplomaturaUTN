package com.utn.diplomaturautn.service;

import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.model.Employee;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

    UserDetails loadUserByUsername(String username);

    UserDetails getByUsername(String username);

}
