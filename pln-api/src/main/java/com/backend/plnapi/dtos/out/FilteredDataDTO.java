package com.backend.plnapi.dtos.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
public class FilteredDataDTO {

    @JsonProperty(value = "benchmark_name")
    private String benchmarkName;

    @JsonProperty(value = "start_date")
    private LocalDate startDate;

    @JsonProperty(value = "end_date")
    private LocalDate endDate;

    @JsonProperty(value = "sum_new_cases")
    private BigInteger sumNewCases;

    @JsonProperty(value = "sum_total_cases")
    private BigInteger sumTotalCases;

}
