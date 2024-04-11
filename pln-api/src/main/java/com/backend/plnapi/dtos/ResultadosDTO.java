package com.backend.plnapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ResultadosDTO {

    private Map<String, TotalCasosDTO> total;

    @JsonProperty(value = "new")
    private Map<String, TotalCasosDTO> newCases;

}
