package com.utn.diplomaturautn.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallDTO {

    private String originPhone;

    private String destinationPhone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateTime;

    private int duration;
}
