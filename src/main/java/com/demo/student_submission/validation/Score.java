package com.demo.student_submission.validation;

import jakarta.persistence.Table;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = ScoreValidator.class)
    public @interface Score{
        String message() default "Invalid Data";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

