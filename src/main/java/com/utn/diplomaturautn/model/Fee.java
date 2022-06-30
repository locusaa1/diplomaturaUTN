package com.utn.diplomaturautn.model;

import com.utn.diplomaturautn.dataTransferObject.FeeResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fees")
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_origin_city")
    private City originCity;

    @OneToOne
    @JoinColumn(name = "id_destination_city")
    private City destinationCity;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

    public FeeResponseDTO fromFeeToResponseDTO() {

        return new FeeResponseDTO(
                this.getId(),
                this.originCity.getName(),
                this.destinationCity.getName(),
                this.cost,
                this.startTime,
                this.endTime);
    }

    public static List<FeeResponseDTO> fromFeeListToResponseDTO(List<Fee> fees) {

        List<FeeResponseDTO> feesResponse = new ArrayList<>();

        for (Fee f : fees) {

            feesResponse.add(f.fromFeeToResponseDTO());
        }
        return feesResponse;
    }
}
