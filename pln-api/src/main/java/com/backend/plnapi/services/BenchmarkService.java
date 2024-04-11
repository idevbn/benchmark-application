package com.backend.plnapi.services;

public interface BenchmarkService {

    void saveBenchmark(
            final String benchmarkName,
            final String firstCountry,
            final String secondCountry
    );

}
