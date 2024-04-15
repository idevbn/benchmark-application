package com.backend.plnapi.dtos.in;

import com.backend.plnapi.models.Benchmark;
import com.backend.plnapi.validators.BenchmarkNameAlreadyExistsValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * DTO com dados de entrada para atualizção do nome de uma {@link Benchmark}
 */
@Data
public class BenchmarkUpdateDTO {

    @BenchmarkNameAlreadyExistsValidator
    @JsonProperty(value = "benchmark_name")
    @NotBlank(message = "{benchmark.validation.name}")
    private String benchmarkName;

}
