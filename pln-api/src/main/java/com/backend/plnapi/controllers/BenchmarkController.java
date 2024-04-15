package com.backend.plnapi.controllers;

import com.backend.plnapi.dtos.in.BenchmarkInputDTO;
import com.backend.plnapi.dtos.in.BenchmarkUpdateDTO;
import com.backend.plnapi.dtos.out.FilteredDataDTO;
import com.backend.plnapi.models.Benchmark;
import com.backend.plnapi.services.BenchmarkService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/benchmark")
public class BenchmarkController {

    private final BenchmarkService benchmarkService;

    public BenchmarkController(final BenchmarkService benchmarkService) {
        this.benchmarkService = benchmarkService;
    }

    @PostMapping
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

    /**
     * Recupera os dados de uma {@link Benchmark} com base no nome da
     * benchmark informado.
     * <p>
     * Observação: os dados devem ser recuperados retornando um DTO específico.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getDataFromBenchmark(
            @PathVariable final Long id
    ) {
        final Benchmark benchmark = this.benchmarkService.findByBenchmarkId(id);

        return ResponseEntity.status(HttpStatus.OK).body(benchmark);
    }

    //TODO: Implementar endpoint que filtra dados com base em request params de datas informadas.
    // O service deve realizar os cálculos filtrando pelas datas fornecidas. Pode-se calcular os
    // casos totais e novos dos países no período.
    // Antes de realizar o processamento dos cálculos, devem ser concretizadas validações, por
    // exemplo, nos inputs de datas, assim como no nome da benchmark informado.
    // O processamento de filtragem deve considerar que os dados a serem filtrados estaão salvos
    // na base de dados com o formato de jsonb.

    @GetMapping(value = "/filter/{id}")
    public ResponseEntity<FilteredDataDTO> getFilteredDataFromBenchmark(
            @PathVariable final Long id,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd")
            final LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd")
            final LocalDate endDate
    ) {
        final FilteredDataDTO filteredData = this.benchmarkService
                .getFilteredData(id, startDate, endDate);

        return ResponseEntity.status(HttpStatus.OK).body(filteredData);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateBenchmarkName(
            @PathVariable final Long id,
            @RequestBody @Valid final BenchmarkUpdateDTO dto
    ) {
        this.benchmarkService.updateBenchmark(id, dto.getBenchmarkName());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDataFromBenchmark(
            @PathVariable final Long id
    ) {
        this.benchmarkService.deleteBenchmark(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
