package com.utn.diplomaturautn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*@Data //Avoids boilerplate code (getters, setters, toString). (Lombok)
@AllArgsConstructor //AutoGenerates the constructor with all the parameters for the class. (Lombok)
@NoArgsConstructor //AutoGenerates the constructor with no parameters for the class. (Lombok)
@Entity //Specifies that the class is an entity of the dB. (persistence)
@Table(name = "persons") //Specifies the table in the database with which this entity is mapped. (persistence)

public class Person {

    @Id //Specifies the primary key of the entity. (persistence)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Specifies the generation strategy for the values of primary key. (persistence)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;


}*/
