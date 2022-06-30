package com.utn.diplomaturautn.dataTransferObject;

import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {

    private int id;

    private String name;

    private String lastName;

    private String dni;

    private String city;

    private String phoneNumber;

    private String username;

    private ClientCondition condition;

    private UserType userType;
}
