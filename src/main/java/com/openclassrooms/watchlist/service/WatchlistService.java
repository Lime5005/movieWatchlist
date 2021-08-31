package com.openclassrooms.watchlist.service;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {
    // How to loss coupling these 2 dependencies from this service for unit test?
    // @Autowired, @Service, Inject into the constructor as Spring beans: bean to bean.

    WatchlistRepository watchlistRepository;
    // Get the rating from API, if rating exists, replace any input with it.
    MovieRatingService movieRatingService;

    @Autowired
    public WatchlistService(WatchlistRepository watchlistRepository, MovieRatingService movieRatingService) {
        super();
        this.watchlistRepository = watchlistRepository;
        this.movieRatingService = movieRatingService;
    }

    public List<WatchlistItem> getWatchlistItems() {
        List<WatchlistItem> watchlistItems = watchlistRepository.getList();
        for (WatchlistItem watchlistItem : watchlistItems) {
            String rating = movieRatingService.getMovieRating(watchlistItem.getTitle());
            if (rating != null) {
                watchlistItem.setRating(rating);
            }
        }

        return watchlistItems;
    }

    public int getWatchlistItemsSize() {
        return watchlistRepository.getList().size();
    }

    public WatchlistItem findWatchlistItemById(Integer id) {
        return watchlistRepository.findById(id);
    }

    // If the service class need to call it, then get from repository.
    public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {
        WatchlistItem item = findWatchlistItemById(watchlistItem.getId());
        if (item == null) {
            // If it's a new id, then check the given title if it's also new:
            if (watchlistRepository.findByTitle(watchlistItem.getTitle()) != null) {
                throw new DuplicateTitleException();
            }
            watchlistRepository.addItem(watchlistItem);
        } else {
            item.setTitle(watchlistItem.getTitle());
            item.setRating(watchlistItem.getRating());
            item.setPriority(watchlistItem.getPriority());
            item.setComment(watchlistItem.getComment());
        }
    }
}
