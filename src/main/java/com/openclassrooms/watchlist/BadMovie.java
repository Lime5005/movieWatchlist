package com.openclassrooms.watchlist;

import com.openclassrooms.watchlist.validation.BadMovieValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)// Applicable to classes.
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BadMovieValidator.class)
public @interface BadMovie {
    String message() default "A movie rating less than 6 should enter at least 15 characters in comment.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
