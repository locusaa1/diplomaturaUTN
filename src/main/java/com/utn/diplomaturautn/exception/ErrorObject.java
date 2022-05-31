package com.utn.diplomaturautn.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Time;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ErrorObject {

    private Integer statusCode;

    private String message;

    private Time timestamp;
}
