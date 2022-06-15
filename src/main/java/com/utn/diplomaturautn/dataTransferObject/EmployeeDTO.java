package com.utn.diplomaturautn.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.diplomaturautn.enumerated.EmployeeCondition;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO extends UserDTO {

    private EmployeeCondition condition;
}
