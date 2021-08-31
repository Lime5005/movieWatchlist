package com.openclassrooms.watchlist.validation;

import com.openclassrooms.watchlist.Rating;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<Rating, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        double number;
        try {
            number = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.out.println("Not a number format!");
            return false;
        }
        return number <= 10 && number >= 1;
    }
}
