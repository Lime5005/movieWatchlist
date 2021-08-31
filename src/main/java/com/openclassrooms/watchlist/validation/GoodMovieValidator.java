package com.openclassrooms.watchlist.validation;

import com.openclassrooms.watchlist.GoodMovie;
import com.openclassrooms.watchlist.domain.WatchlistItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem> {
    @Override
    public boolean isValid(WatchlistItem watchlistItem, ConstraintValidatorContext constraintValidatorContext) {
        return !(Double.parseDouble(watchlistItem.getRating()) >= 8
                && "L".equals(watchlistItem.getPriority().trim().toUpperCase(Locale.ROOT)));
        //return false if the condition is met.
    }
}
