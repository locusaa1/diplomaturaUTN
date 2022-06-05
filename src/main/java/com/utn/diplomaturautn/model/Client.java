package com.utn.diplomaturautn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "id_person")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @OneToOne
    @JoinColumn(name = "id_phone")
    private Phone phone;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "condition")
    private String active;
}
