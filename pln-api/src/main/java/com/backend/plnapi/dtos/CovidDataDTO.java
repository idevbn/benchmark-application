package com.backend.plnapi.dtos;

import lombok.Data;

import java.util.Map;

/**
 * DTO que recupera os dados obtidos da API externa.
 */
@Data
public class CovidDataDTO {

    private String country;
    private String region;
    private Map<String, Object> cases;

}
