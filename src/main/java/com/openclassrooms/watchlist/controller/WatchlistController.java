package com.openclassrooms.watchlist.controller;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchlistController {

    private final List<WatchlistItem> watchlistItemLists = new ArrayList<>();
    private static int index = 1;

    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {
        String viewName = "watchlistItemForm";
        Map<String, Object> model = new HashMap<>();
        WatchlistItem watchlistItem = findWatchlistItemById(id);
        if (watchlistItem == null) {
            model.put("watchlistItem", new WatchlistItem());
        } else {
            model.put("watchlistItem", watchlistItem);
        }
        return new ModelAndView(viewName, model);
    }

    private WatchlistItem findWatchlistItemById(Integer id) {
        // Loop through all the list to find the item:
        for (WatchlistItem watchlistItem : watchlistItemLists) {
            if(watchlistItem.getId().equals(id)) {
                return watchlistItem;
            }
        }
        return null;
    }


    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistFrom(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {
        // If valid is not fully accomplished, return a blank form:
        if (bindingResult.hasErrors()) {
            return new ModelAndView("watchlistItemForm");
        }
        WatchlistItem item = findWatchlistItemById(watchlistItem.getId());
        if (item == null) {
            // If it's a new id, then check the given title if it's also new:
            if (titleAlreadyExists(watchlistItem.getTitle())) {
                bindingResult.rejectValue("title", "", "This title already exists on your watchlist.");
                return new ModelAndView("watchlistItemForm");
            }
            watchlistItem.setId(index++);
            watchlistItemLists.add(watchlistItem);
        } else {
            item.setTitle(watchlistItem.getTitle());
            item.setRating(watchlistItem.getRating());
            item.setPriority(watchlistItem.getPriority());
            item.setComment(watchlistItem.getComment());
            // Note that the id should be original, how to find it? Set a hidden input field.

        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");

        return new ModelAndView(redirectView);
    }

    private boolean titleAlreadyExists(String title) {
        for (WatchlistItem item : watchlistItemLists) {
            if (item.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {

        /*watchlistItemLists.clear();// Clear previous call.
        watchlistItemLists.add(new WatchlistItem("Lion King","8.5", "high","Very good!", index++ ));
        watchlistItemLists.add(new WatchlistItem("Back to the future","9.5", "high","Very good!", index++));
        watchlistItemLists.add(new WatchlistItem("Sex and the city","7.5", "high","Very good!", index++));
        watchlistItemLists.add(new WatchlistItem("Emily in Paris","8.5", "high","Very good!", index++));
*/
        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<>();
        model.put("watchlistItems", watchlistItemLists);
        model.put("numberOfMovies", watchlistItemLists.size());
        return new ModelAndView(viewName, model);
    }
}
