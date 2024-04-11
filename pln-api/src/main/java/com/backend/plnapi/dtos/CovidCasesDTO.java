package com.backend.plnapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CovidCasesDTO {

    private long total;

    @JsonProperty(value = "new")
    private long newCases;

}
