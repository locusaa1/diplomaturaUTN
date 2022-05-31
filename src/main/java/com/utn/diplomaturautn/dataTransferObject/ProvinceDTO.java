package com.utn.diplomaturautn.dataTransferObject;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDTO {

    private int id;

    @NotNull(message = "The name field can not be null.")
    @NotBlank(message = "The name field is mandatory and not blank.")
    @Size(max = 50, min = 1, message = "The name must have min:1 character and max:50")
    private String name;
}
