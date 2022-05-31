package com.utn.diplomaturautn.model;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "calls")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_destination_phone")
    private Phone originPhone;

    @OneToOne
    @JoinColumn(name = "id_origin_phone")
    private Phone destinationPhone;

    @OneToOne
    @JoinColumn(name = "id_origin_city")
    private City originCity;

    @OneToOne
    @JoinColumn(name = "id_destination_city")
    private City destinationCity;

    @OneToOne
    @JoinColumn(name = "id_fee")
    private Fee fee;

    @OneToOne
    @JoinColumn(name = "id_bill")
    private Bill bill;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "duration")
    private int duration;

    @Column(name = "total")
    private double total;
}
