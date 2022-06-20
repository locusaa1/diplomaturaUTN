package com.utn.diplomaturautn.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import com.utn.diplomaturautn.model.City;
import com.utn.diplomaturautn.model.Phone;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("clientDTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO extends UserDTO {

    private String phoneNumber;

    private ClientCondition condition;

    @Builder
    public ClientDTO(int cityId, String name, String lastName, String dni, String username, String password, UserType userType, String phoneNumber, ClientCondition condition) {

        this.setCityId(cityId);
        this.setName(name);
        this.setLastName(lastName);
        this.setDni(dni);
        this.setUsername(username);
        this.setPassword(password);
        this.setUserType(userType);
        this.setPhoneNumber(phoneNumber);
        this.condition = condition;
    }
}
