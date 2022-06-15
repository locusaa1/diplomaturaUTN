package com.utn.diplomaturautn.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface JwtService {

    String createToken(UserDetails userDetails);

    Boolean hasTokenExpired(String token);

    Boolean validateToken(String token, UserDetails userDetails);

    String extractUsername(String token);

    Collection<? extends GrantedAuthority> getAuthorities(String token);
}
