package com.backend.plnapi.validators.constraint;

import com.backend.plnapi.repositories.BenchmarkRepository;
import com.backend.plnapi.validators.BenchmarkNameAlreadyExistsValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class BenchmarkNameAlreadyExistsConstraintValidator
        implements ConstraintValidator<BenchmarkNameAlreadyExistsValidator, String> {

    private final BenchmarkRepository repository;

    public BenchmarkNameAlreadyExistsConstraintValidator(final BenchmarkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(final BenchmarkNameAlreadyExistsValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(final String benchmarkName,
                           final ConstraintValidatorContext ctx) {
        return !this.repository.existsByBenchmarkName(benchmarkName);
    }

}
