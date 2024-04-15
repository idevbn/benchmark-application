package com.backend.plnapi.services.impl;

import com.backend.plnapi.clients.CovidApiClient;
import com.backend.plnapi.dtos.*;
import com.backend.plnapi.exceptions.BenchmarkNotFoundException;
import com.backend.plnapi.models.Benchmark;
import com.backend.plnapi.repositories.BenchmarkRepository;
import com.backend.plnapi.services.BenchmarkService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.HashMap;

@Log4j2
@Service
public class BenchmarkServiceImpl implements BenchmarkService {

    private final BenchmarkRepository benchmarkRepository;
    private final CovidApiClient covidApiClient;

    public BenchmarkServiceImpl(
            final BenchmarkRepository benchmarkRepository,
            final CovidApiClient covidApiClient
    ) {
        this.benchmarkRepository = benchmarkRepository;
        this.covidApiClient = covidApiClient;
    }

    /**
     * Implementação do método que salva uma benchmark.
     * São realizadas duas requisições com o nome de um país
     * por requisição e o nome da benchmark que conterá esses dados
     * a serem persistidos.
     */
    @Override
    @Transactional
    public void saveBenchmark(final String benchmarkName,
                              final String firstCountry,
                              final String secondCountry) {
        try {
            final CovidDataDTO dataByFirstCountry = this.covidApiClient
                    .getDataByCountry(firstCountry);

            final CovidDataDTO dataBySecondCountry = this.covidApiClient
                    .getDataByCountry(secondCountry);

            final CovidDataCountryDTO dataFirstCountry = (CovidDataCountryDTO) dataByFirstCountry;
            final CovidDataCountryDTO dataSecondCountry = (CovidDataCountryDTO) dataBySecondCountry;

            final ResultadosDTO resultadosDTO = new ResultadosDTO();
            resultadosDTO.setTotal(new HashMap<>());
            resultadosDTO.setNewCases(new HashMap<>());

            dataFirstCountry.getCases().forEach((dia, dto) -> {
                resultadosDTO.getTotal().computeIfAbsent(dia, total -> TotalCasosDTO.fromFirst(dto.getTotal()));
                resultadosDTO.getTotal().computeIfPresent(dia, (k, v) -> {
                    v.setTotalFirstCountry(dto.getTotal());
                            return v;
                });
            });

            dataSecondCountry.getCases().forEach((dia, dto) -> {
                resultadosDTO.getTotal().computeIfAbsent(dia, total -> TotalCasosDTO.fromSecond(dto.getTotal()));
                resultadosDTO.getTotal().computeIfPresent(dia, (k, v) -> {
                    v.setTotalSecondCountry(dto.getTotal());
                    return v;
                });
            });

            dataFirstCountry.getCases().forEach((dia, dto) -> {
                resultadosDTO.getNewCases().computeIfAbsent(dia, newCases -> NovosCasosDTO.fromFirst(dto.getNewCases()));
                resultadosDTO.getNewCases().computeIfPresent(dia, (k, v) -> {
                    v.setNewCasesFirstCountry(dto.getNewCases());
                    return v;
                });
            });

            dataSecondCountry.getCases().forEach((dia, dto) -> {
                resultadosDTO.getNewCases().computeIfAbsent(dia, newCases -> NovosCasosDTO.fromSecond(dto.getNewCases()));
                resultadosDTO.getNewCases().computeIfPresent(dia, (k, v) -> {
                    v.setNewCasesSecondCountry(dto.getNewCases());
                    return v;
                });
            });

            //TODO: Criar método que busca, de uma única vez, das datas mínimas e máximas gerais
            // dos países -> mínimo e máximo entre dataFirstCountry e dataSecondCountry.

            final Benchmark benchmark = Benchmark.of(
                    benchmarkName,
                    dataByFirstCountry.getCountry(),
                    dataBySecondCountry.getCountry(),
                    dataFirstCountry.getCases().keySet()
                            .stream().min(Comparator.naturalOrder()).get(),
                    dataFirstCountry.getCases().keySet()
                            .stream().max(Comparator.naturalOrder()).get(),
                    resultadosDTO
            );

            this.benchmarkRepository.save(benchmark);
        } catch (final Exception e) {
            log.error("Error: ", e);
        }
    }

    @Override
    public Benchmark findByBenchmarkId(final Long id) {
        final Benchmark benchmark = this.benchmarkRepository.findById(id)
                .orElseThrow(() -> new BenchmarkNotFoundException(id));

        return benchmark;
    }

    @Override
    @Transactional
    public void deleteBenchmark(final Long id) {
        final boolean exists = this.benchmarkRepository.existsById(id);

        if (!exists) {
            throw new BenchmarkNotFoundException(id);
        }

        this.benchmarkRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateBenchmark(final Long id, final String benchmarkName) {
        final boolean exists = this.benchmarkRepository.existsById(id);

        if (!exists) {
            throw new BenchmarkNotFoundException(id);
        }

        this.benchmarkRepository.updateBenchmarkName(benchmarkName, id);
    }

}
