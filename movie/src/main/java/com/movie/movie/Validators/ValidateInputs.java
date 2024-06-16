package com.movie.movie.Validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InputValidator.class)
@Documented
public @interface ValidateInputs {
    
    String message() default "Missing input parameter(s)";

    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default{};
    
    long movieid();
    String directorname();
}
