package com.utn.diplomaturautn.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.diplomaturautn.enumerated.ClientCondition;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO extends UserDTO {

    private String phoneNumber;

    private ClientCondition condition;
}
