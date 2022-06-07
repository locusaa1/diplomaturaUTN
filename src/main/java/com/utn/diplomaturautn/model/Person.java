package com.utn.diplomaturautn.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

//@Data Avoids boilerplate code (getters, setters, toString). (Lombok)
@Getter
@Setter
@ToString
@Builder //Provides static method to build the Bean
@Component
@AllArgsConstructor //AutoGenerates the constructor with all the parameters for the class. (Lombok)
@NoArgsConstructor //AutoGenerates the constructor with no parameters for the class. (Lombok)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Client.class, name = "client"),
        @JsonSubTypes.Type(value = Employee.class, name = "employee")
})
@Entity //Specifies that the class is an entity of the dB. (persistence)
@Table(name = "persons") //Specifies the table in the database with which this entity is mapped. (persistence)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id //Specifies the primary key of the entity. (persistence)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Specifies the generation strategy for the values of primary key. (persistence)
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
