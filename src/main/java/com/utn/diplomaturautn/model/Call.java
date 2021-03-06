package com.utn.diplomaturautn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

import static java.util.TimeZone.getTimeZone;

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
    @JoinColumn(name = "id_origin_phone")
    private Phone originPhone;

    @OneToOne
    @JoinColumn(name = "id_destination_phone")
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "duration")
    private int duration;

    @Column(name = "total")
    private double total;
}
