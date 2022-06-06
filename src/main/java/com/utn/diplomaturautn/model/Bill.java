package com.utn.diplomaturautn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "calls_sum")
    private int callsSum;

    @Column(name = "total")
    private double total;

    @Column(name = "generated_date")
    private Date generatedDate;

    @Column(name = "expiration_date")
    private Date expirationDate;
}
