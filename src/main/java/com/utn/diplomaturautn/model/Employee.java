package com.utn.diplomaturautn.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.enumerated.EmployeeCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import lombok.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Component
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("employee")
@Entity
@Table(name = "employees")
public class Employee extends User implements UserDetails {

    @Column(name = "employee_condition")
    @Enumerated(value = EnumType.STRING)
    private EmployeeCondition condition;

    @Builder
    public Employee(City city, String name, String lastName, String dni, String username, String password, UserType userType, EmployeeCondition condition) {

        this.setCity(city);
        this.setName(name);
        this.setLastName(lastName);
        this.setDni(dni);
        this.setUsername(username);
        this.setPassword(password);
        this.setUserType(userType);
        this.condition = condition;
    }

    public Employee modifyUsingEmployee(Employee newData) {

        this.modifyUsingUser(newData);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getUserType().toString()));
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return (this.getCondition() == EmployeeCondition.ACTIVE);
    }
}
