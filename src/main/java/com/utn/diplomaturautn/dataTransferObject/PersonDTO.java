package com.utn.diplomaturautn.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClientDTO.class, name = "clientDTO"),
        @JsonSubTypes.Type(value = EmployeeDTO.class, name = "employeeDTO")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class PersonDTO {

    private int id;

    private int cityId;

    private String name;

    private String lastName;

    private String dni;
}
