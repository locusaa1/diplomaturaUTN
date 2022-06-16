package com.utn.diplomaturautn.model;

import com.utn.diplomaturautn.enumerated.UserType;
import lombok.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

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

    protected void modifyUsingUser(User newData) {

        if (!StringUtils.isBlank(newData.getName()) && !this.getName().equals(newData.getName())) {

            this.setName(newData.getName());
        }
        if (!StringUtils.isBlank(newData.getLastName()) && !this.getLastName().equals(newData.getLastName())) {

            this.setLastName(newData.getLastName());
        }
        if (!StringUtils.isBlank(newData.getDni()) && !this.getDni().equals(newData.getDni())) {

            this.setDni(newData.getDni());
        }
        if (!StringUtils.isBlank(newData.getUsername()) && !this.getUsername().equals(newData.getUsername())) {

            this.setUsername(newData.getUsername());
        }
        if (!StringUtils.isBlank(newData.getPassword()) && !this.getPassword().equals(newData.getPassword())) {

            this.setPassword(newData.getPassword());
        }
        if (!Objects.isNull(newData.getCity()) && !this.getCity().equals(newData.getCity())) {

            this.setCity(newData.getCity());
        }
    }
}
