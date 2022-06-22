package com.utn.diplomaturautn.config;

import com.utn.diplomaturautn.enumerated.UserType;
import com.utn.diplomaturautn.filter.JwtAuthorizationFilter;
import com.utn.diplomaturautn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurity {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurity(JwtAuthorizationFilter jwtAuthorizationFilter, UserService userService, PasswordEncoder passwordEncoder) {

        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                //Authorization permit all for swagger
                .authorizeRequests()
                .antMatchers("/swagger-ui", "/swagger-ui.html", "/open-api/swagger-ui-custom.html", "/swagger-ui/index.html", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .antMatchers("/v2/api-docs/**", "/v3/api-docs/**").permitAll()
                .and()
                //Authorization permit all for login
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/person/login").permitAll()
                .and()
                //Authorization POST map for PersonController with Employee usertype
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/person/client",
                        "/api/person/employee").hasAuthority(UserType.EMPLOYEE.toString())
                .and()
                //Authorization PATCH map for the PersonController with Employee usertype
                .authorizeRequests()
                .antMatchers(HttpMethod.PATCH, "/api/person/client/{id}",
                        "/api/person/client/discontinue/{id}",
                        "/api/person/client/reactivate/{id}",
                        "/api/person/employee/{id}",
                        "/api/person/employee/reactivate/{id}").hasAuthority(UserType.EMPLOYEE.toString())
                .and()
                //Authorization DELETE map for the PersonController with Employee usertype
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/person/client/{id}",
                        "/api/person/employee/{id}").hasAuthority(UserType.EMPLOYEE.toString())
                .and()
                //Authorization GET map for the FeeController with Employee usertype
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/fee/",
                        "/api/fee/{id}").hasAuthority(UserType.EMPLOYEE.toString())
                .and()
                //Authorization POST map for the CallController with ANTENNA usertype
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/call/").hasAuthority(UserType.ANTENNA.toString())
                .and()
                //Authorization GET map for the CallController with CLIENT usertype
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/call/date/").hasAuthority(UserType.CLIENT.toString())
                .and()
                //Authorization GET map for the CallController with EMPLOYEE usertype
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/call/date/",
                        "/api/call/client/{id}/date/",
                        "/api/call/{id}",
                        "/api/call/").hasAuthority(UserType.EMPLOYEE.toString())
                .and()
                //Authorization GET map for the BillController with CLIENT usertype
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/bill/date").hasAuthority(UserType.CLIENT.toString())
                .and()
                //Authorization GET map for the BillController with EMPLOYEE usertype
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/bill/date",
                        "/api/bill/date&client",
                        "/api/bill/{id}",
                        "/api/bill/").hasAuthority(UserType.EMPLOYEE.toString())
                .anyRequest().authenticated();

        return http.build();
    }
}

