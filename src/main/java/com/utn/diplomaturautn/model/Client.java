package com.utn.diplomaturautn.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Component
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("client")
@Entity
@Table(name = "clients")
public class Client extends User implements UserDetails {

    @OneToOne
    @JoinColumn(name = "id_phone")
    private Phone phone;

    @Column(name = "client_condition")
    @Enumerated(value = EnumType.STRING)
    private ClientCondition condition;

    @Builder
    public Client(City city, String name, String lastName, String dni, String username, String password, UserType userType, Phone phone, ClientCondition condition) {

        this.setCity(city);
        this.setName(name);
        this.setLastName(lastName);
        this.setDni(dni);
        this.setUsername(username);
        this.setPassword(password);
        this.setUserType(userType);
        this.phone = phone;
        this.condition = condition;
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

        return (this.getCondition() == ClientCondition.ACTIVE);
    }
}
