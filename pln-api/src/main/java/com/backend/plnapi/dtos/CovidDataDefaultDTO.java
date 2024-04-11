package com.backend.plnapi.dtos;

import lombok.Data;

@Data
public final class CovidDataDefaultDTO extends CovidDataDTO {

    private CovidCasesDTO cases;

}
