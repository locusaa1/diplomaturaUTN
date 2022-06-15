package com.utn.diplomaturautn.filter;

import com.utn.diplomaturautn.exception.ResourceNotFoundException;
import com.utn.diplomaturautn.service.ClientService;
import com.utn.diplomaturautn.service.EmployeeService;
import com.utn.diplomaturautn.service.JwtService;
import com.utn.diplomaturautn.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final String HEADER_TOKEN_PREFIX = "Bearer ";

    private static final String HEADER_AUTHORIZATION = "Authorization";

    private final UserService userService;

    private final JwtService jwtService;

    @Autowired
    public JwtAuthorizationFilter(UserService userService, JwtService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(HEADER_TOKEN_PREFIX)) {

            String token = authorizationHeader.replace(HEADER_TOKEN_PREFIX, "");
            String username = jwtService.extractUsername(token);
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
