package com.openclassrooms.watchlist.service;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.repository.WatchlistRepository;

import java.util.List;

public class WatchlistService {
    WatchlistRepository watchlistRepository = new WatchlistRepository();

    // If the controller class need to call it, then ask it to get from this service.
    public List<WatchlistItem> getWatchlistItems() {
        return watchlistRepository.getList();
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
