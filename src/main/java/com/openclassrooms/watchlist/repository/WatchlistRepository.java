package com.openclassrooms.watchlist.repository;

import com.openclassrooms.watchlist.domain.WatchlistItem;

import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository {

    private final List<WatchlistItem> watchlistItemLists = new ArrayList<>();
    private static int index = 1;

    public List<WatchlistItem> getList() {
        return watchlistItemLists;
    }

    public void addItem(WatchlistItem item) {
        item.setId(index++);
        watchlistItemLists.add(item);
    }

    public WatchlistItem findById(Integer id) {
        for (WatchlistItem item : watchlistItemLists) {
            if(item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public WatchlistItem findByTitle(String title) {
        for (WatchlistItem item : watchlistItemLists) {
            if(item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;
    }
}
