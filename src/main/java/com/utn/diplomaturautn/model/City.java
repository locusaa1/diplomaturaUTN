package com.utn.diplomaturautn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_province", nullable = false)
    private Province province;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "area_code", nullable = false, length = 30)
    private String areaCode;

    @Column(name = "postal_code", nullable = false, length = 30)
    private String postalCode;


}
