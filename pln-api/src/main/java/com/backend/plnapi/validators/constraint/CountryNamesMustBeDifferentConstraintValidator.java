package com.backend.plnapi.validators.constraint;

import com.backend.plnapi.dtos.in.BenchmarkInputDTO;
import com.backend.plnapi.validators.CountryNamesMustBeDifferentValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CountryNamesMustBeDifferentConstraintValidator
        implements ConstraintValidator<CountryNamesMustBeDifferentValidator, BenchmarkInputDTO> {

    @Override
    public void initialize(final CountryNamesMustBeDifferentValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(
            final BenchmarkInputDTO dto,
            final ConstraintValidatorContext context
    ) {

        final String firstCountryName = dto.getFirstCountryName();
        final String secondCountryName = dto.getSecondCountryName();

        return !firstCountryName.equals(secondCountryName);
    }

}
