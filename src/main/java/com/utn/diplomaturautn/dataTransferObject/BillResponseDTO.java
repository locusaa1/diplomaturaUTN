package com.utn.diplomaturautn.dataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDTO {

    private int id;

    private String client;

    private int callsAmount;

    private double total;

    private Date generatedDate;

    private Date expirationDate;
}
