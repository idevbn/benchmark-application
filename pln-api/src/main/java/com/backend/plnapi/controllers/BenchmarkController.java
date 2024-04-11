package com.backend.plnapi.controllers;

import com.backend.plnapi.dtos.in.BenchmarkInputDTO;
import com.backend.plnapi.services.BenchmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BenchmarkController {

    private final BenchmarkService benchmarkService;

    public BenchmarkController(final BenchmarkService benchmarkService) {
        this.benchmarkService = benchmarkService;
    }

    @PostMapping(value = "/benchmark")
    public ResponseEntity<Void> createBenchmark(
            @RequestBody @Valid final BenchmarkInputDTO benchmarkInputDTO
    ) {

        this.benchmarkService.saveBenchmark(
                benchmarkInputDTO.getBenchmark(),
                benchmarkInputDTO.getFirstCountryName(),
                benchmarkInputDTO.getSecondCountryName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
