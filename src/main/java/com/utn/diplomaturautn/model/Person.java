package com.utn.diplomaturautn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data //Avoids boilerplate code (getters, setters, toString). (Lombok)
@Builder //Provides static method to build the Bean
@Component
@AllArgsConstructor //AutoGenerates the constructor with all the parameters for the class. (Lombok)
@NoArgsConstructor //AutoGenerates the constructor with no parameters for the class. (Lombok)
@Entity //Specifies that the class is an entity of the dB. (persistence)
@Table(name = "persons") //Specifies the table in the database with which this entity is mapped. (persistence)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id //Specifies the primary key of the entity. (persistence)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Specifies the generation strategy for the values of primary key. (persistence)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_city")
    private City city;

    @Column(name = "name", nullable = false) //Specifies the column name and his attributes
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;
}
