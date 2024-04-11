package com.backend.plnapi.validators;
import com.backend.plnapi.validators.constraint.CountryNamesMustBeDifferentConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = CountryNamesMustBeDifferentConstraintValidator.class)
public @interface CountryNamesMustBeDifferentValidator {

    String message() default "{benchmark.validation.differentNames}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
