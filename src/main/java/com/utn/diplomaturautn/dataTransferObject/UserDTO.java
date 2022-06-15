package com.utn.diplomaturautn.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.diplomaturautn.enumerated.UserType;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode(callSuper = true)
@Component
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class UserDTO extends PersonDTO {

    private String username;

    private String password;

    private UserType userType;
}
