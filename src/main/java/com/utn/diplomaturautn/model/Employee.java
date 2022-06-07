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
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("employee")
@Entity
@Table(name = "employees")
public class Employee extends Person {

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "condition")
    @Enumerated(value = EnumType.STRING)
    private String condition;
}
