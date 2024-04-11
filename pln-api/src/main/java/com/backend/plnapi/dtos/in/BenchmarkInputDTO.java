package com.backend.plnapi.dtos.in;

import com.backend.plnapi.validators.CountryNamesMustBeDifferentValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@CountryNamesMustBeDifferentValidator
public class BenchmarkInputDTO implements Serializable {

    private String benchmark;

    @JsonProperty(value = "first_country_name")
    private String firstCountryName;

    @JsonProperty(value = "second_country_name")
    private String secondCountryName;

}
