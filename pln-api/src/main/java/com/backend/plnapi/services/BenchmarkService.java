package com.backend.plnapi.services;

import com.backend.plnapi.models.Benchmark;

public interface BenchmarkService {

    void saveBenchmark(
            final String benchmarkName,
            final String firstCountry,
            final String secondCountry
    );

    Benchmark findByBenchmarkId(final Long id);

    void deleteBenchmark(final Long id);

    void updateBenchmark(final Long id, final String benchmarkName);

}
