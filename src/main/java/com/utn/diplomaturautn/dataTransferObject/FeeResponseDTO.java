package com.utn.diplomaturautn.dataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeResponseDTO {

    private int id;

    private String originCity;

    private String destinationCity;

    private double cost;

    private Time startTime;

    private Time endTime;
}
