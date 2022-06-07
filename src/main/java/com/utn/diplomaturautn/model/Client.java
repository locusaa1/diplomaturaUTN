package com.utn.diplomaturautn.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("client")
@Entity
@Table(name = "clients")
public class Client extends Person {

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "id_phone")
    private Phone phone;

    @Column(name = "condition")
    @Enumerated(value = EnumType.STRING)
    private String active;
}
