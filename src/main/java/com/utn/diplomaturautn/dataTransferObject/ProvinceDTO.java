package com.utn.diplomaturautn.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.diplomaturautn.model.Province;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProvinceDTO {

    private Integer id;
    private String name;
    private List<CityDTO> cities;

    public static ProvinceDTO from (Province province){
        return ProvinceDTO.builder().id(province.getId()).name(province.getName()).build();
    }
}
