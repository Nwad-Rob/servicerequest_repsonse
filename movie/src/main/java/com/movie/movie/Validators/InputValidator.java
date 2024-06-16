package com.movie.movie.Validators;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InputValidator implements ConstraintValidator<ValidateInputs,Object> {

    private long movieid;
    private String directorname;

    enum ValidationStatus{
        Pending,Success,Fail
    }

    @Override
    public void initialize(ValidateInputs constaintAnnotation){
        movieid = constaintAnnotation.movieid();
        directorname = constaintAnnotation.directorname();
    }
    
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraint) {
        Field movieidField = FieldUtils.getField(object.getClass(), Long.toString(movieid), true); 
        Field directornameField = FieldUtils.getField(object.getClass(), directorname, true);
        ValidationStatus validationStatus = ValidationStatus.Pending;

        try {
            
            if ((FieldUtils.readField(movieidField , object) != null && StringUtils.isNotEmpty(FieldUtils.readField(movieidField, object).toString()))
                || (FieldUtils.readField(directornameField, object) != null && StringUtils.isNotEmpty(FieldUtils.readField(directornameField, object).toString())))
            {
                if(validationStatus.equals(ValidationStatus.Pending)){
                    validationStatus = ValidationStatus.Success;
                }else{
                    validationStatus = ValidationStatus.Fail;
                }
            }
        } catch (IllegalAccessException e) {
            constraint.disableDefaultConstraintViolation();
            constraint.buildConstraintViolationWithTemplate("All required elements must be entered: Validation field is not declared public").addConstraintViolation();
        }

        return (validationStatus.equals(ValidationStatus.Success) ? true : false);
    }
    
}
