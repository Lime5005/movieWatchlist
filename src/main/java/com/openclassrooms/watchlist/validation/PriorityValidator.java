package com.openclassrooms.watchlist.validation;

import com.openclassrooms.watchlist.Priority;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Locale;

public class PriorityValidator implements ConstraintValidator<Priority, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.trim().length() == 1 && "LMH".contains(value.toUpperCase(Locale.ROOT));
    }
}
