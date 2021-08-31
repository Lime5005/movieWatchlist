package com.openclassrooms.watchlist;

import com.openclassrooms.watchlist.validation.GoodMovieValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)// Applicable to classes.
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GoodMovieValidator.class)
public @interface GoodMovie {
    String message() default "A movie rating higher than 8 should be at least priority M.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
