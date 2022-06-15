package com.utn.diplomaturautn.model;

import com.utn.diplomaturautn.enumerated.UserType;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "users")
public abstract class User extends Person {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "user_type")
    @Enumerated(value = EnumType.STRING)
    private UserType userType;
}
