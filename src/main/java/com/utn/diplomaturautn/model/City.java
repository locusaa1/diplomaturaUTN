package com.utn.diplomaturautn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "cities")
public class City {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
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
