package com.utn.diplomaturautn.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "id_person")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

}
