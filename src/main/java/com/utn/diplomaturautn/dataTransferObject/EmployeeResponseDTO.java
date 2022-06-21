package com.utn.diplomaturautn.dataTransferObject;

import com.utn.diplomaturautn.enumerated.EmployeeCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {

    private String name;

    private String lastName;

    private String dni;

    private String city;

    private String username;

    private EmployeeCondition condition;

    private UserType userType;
}
