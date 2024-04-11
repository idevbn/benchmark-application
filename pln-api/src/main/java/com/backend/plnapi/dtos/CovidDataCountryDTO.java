package com.backend.plnapi.dtos;

import lombok.Data;

import java.util.Map;

@Data
public final class CovidDataCountryDTO extends CovidDataDTO {

    private Map<String, CovidCasesDTO> cases;

}
