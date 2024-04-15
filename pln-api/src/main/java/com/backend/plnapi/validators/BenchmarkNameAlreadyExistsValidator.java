package com.backend.plnapi.validators;

import com.backend.plnapi.validators.constraint.BenchmarkNameAlreadyExistsConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Constraint(validatedBy = BenchmarkNameAlreadyExistsConstraintValidator.class)
public @interface BenchmarkNameAlreadyExistsValidator {

    String message() default "{benchmark.validation.name.already.exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
