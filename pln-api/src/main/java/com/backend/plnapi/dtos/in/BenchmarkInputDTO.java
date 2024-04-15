package com.backend.plnapi.dtos.in;

import com.backend.plnapi.validators.BenchmarkNameAlreadyExistsValidator;
import com.backend.plnapi.validators.CountryNamesMustBeDifferentValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@CountryNamesMustBeDifferentValidator
public class BenchmarkInputDTO {

    @BenchmarkNameAlreadyExistsValidator
    @NotBlank(message = "{benchmark.validation.name}")
    private String benchmark;

    @NotBlank(message = "{benchmark.validation.first-country.name}")
    @JsonProperty(value = "first_country_name")
    private String firstCountryName;

    @NotBlank(message = "{benchmark.validation.second-country.name}")
    @JsonProperty(value = "second_country_name")
    private String secondCountryName;

}
