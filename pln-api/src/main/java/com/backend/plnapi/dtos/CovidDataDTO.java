package com.backend.plnapi.dtos;

import lombok.Data;

/**
 * DTO que recupera os dados obtidos da API externa.
 */
@Data
public abstract class CovidDataDTO {

    private String country;
    private String region;

}
