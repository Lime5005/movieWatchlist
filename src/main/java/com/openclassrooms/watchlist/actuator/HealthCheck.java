package com.openclassrooms.watchlist.actuator;

import com.openclassrooms.watchlist.service.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

    private final MovieRatingService movieRatingService;

    @Autowired
    public HealthCheck(MovieRatingService movieRatingService) {
        this.movieRatingService = movieRatingService;
    }

    @Override
    public Health health() {
        if (movieRatingService.getMovieRating("Star wars") == null) {
            return Health.down().withDetail("Cause", "OMDb API is not working.").build();
        }
        return Health.up().build();
    }
}
