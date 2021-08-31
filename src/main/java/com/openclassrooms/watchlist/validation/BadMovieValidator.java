package com.openclassrooms.watchlist.validation;

import com.openclassrooms.watchlist.BadMovie;
import com.openclassrooms.watchlist.domain.WatchlistItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BadMovieValidator implements ConstraintValidator<BadMovie, WatchlistItem> {
    @Override
    public boolean isValid(WatchlistItem watchlistItem, ConstraintValidatorContext constraintValidatorContext) {

        return !(Double.parseDouble(watchlistItem.getRating()) < 6 && watchlistItem.getComment().trim().length() < 15);
    }
}
