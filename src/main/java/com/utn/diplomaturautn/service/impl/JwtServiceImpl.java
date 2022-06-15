package com.utn.diplomaturautn.service.impl;

import com.utn.diplomaturautn.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private static final Integer EXPIRATION_TIME = 1000 * 60 * 60;

    private static final String AUTHORITIES = "authorities";

    private final String SECRET_KEY;

    public JwtServiceImpl() {

        this.SECRET_KEY = Base64.getEncoder().encodeToString("key".getBytes());
    }

    @Override
    public String createToken(UserDetails userDetails) {

        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return Jwts.builder().
                setSubject(username).
                claim(AUTHORITIES, authorities).
                setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
                signWith(SignatureAlgorithm.HS256, SECRET_KEY).
                compact();
    }

    @Override
    public Boolean hasTokenExpired(String token) {

        return Jwts.parser().
                setSigningKey(SECRET_KEY).
                parseClaimsJws(token).
                getBody().
                getExpiration().
                before(new Date());
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {

        String username = extractUsername(token);
        return (userDetails.getUsername().equals(username) && !hasTokenExpired(token));
    }

    @Override
    public String extractUsername(String token) {

        return Jwts.parser().
                setSigningKey(SECRET_KEY).
                parseClaimsJws(token).
                getBody().
                getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(String token) {

        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return (Collection<? extends GrantedAuthority>) claims.get(AUTHORITIES);
    }
}
