package com.backend.plnapi.repositories;

import com.backend.plnapi.models.Benchmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BenchmarkRepository extends JpaRepository<Benchmark, Long> {

    boolean existsByBenchmarkName(final String benchmarkName);

    @Modifying
    @Query(value = "UPDATE tb_benchmark SET benchmark_name = :benchmarkName WHERE id = :id",
            nativeQuery = true)
    void updateBenchmarkName(@Param("benchmarkName") final String benchmarkName,
                             @Param("id") final Long id);

}
