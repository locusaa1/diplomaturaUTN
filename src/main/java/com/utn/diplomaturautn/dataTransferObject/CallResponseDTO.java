package com.utn.diplomaturautn.dataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallResponseDTO {

    private String originPhone;

    private String destinationPhone;

    private String originCity;

    private String destinationCity;

    private double feeCost;

    private Timestamp date;

    private int duration;

    private double total;
}
