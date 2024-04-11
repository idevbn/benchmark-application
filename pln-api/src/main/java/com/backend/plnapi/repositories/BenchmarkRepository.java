package com.backend.plnapi.repositories;

import com.backend.plnapi.models.Benchmark;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BenchmarkRepository  extends MongoRepository<Benchmark, String> {
}
