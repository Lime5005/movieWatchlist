package com.openclassrooms.watchlist;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repository.WatchlistRepository;
import com.openclassrooms.watchlist.service.MovieRatingService;
import com.openclassrooms.watchlist.service.MovieRatingServiceImpl;
import com.openclassrooms.watchlist.service.WatchlistService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {
    // Link the 3 classes together:
    @Mock
    private WatchlistRepository watchlistRepositoryMock;

    @Mock
    private MovieRatingService movieRatingServiceMock;

    @InjectMocks
    private WatchlistService watchlistService;

    @Test
    public void getWatchlistItems_withNewItems_shouldReturnAList() {
        //Arrange: what I designed:
        WatchlistItem item1 = new WatchlistItem("Star Wars", "9.0", "H", "", 1);
        WatchlistItem item2 = new WatchlistItem("Back to the future", "9.0", "H", "", 1);
        List<WatchlistItem> lists = Arrays.asList(item1, item2);
        Mockito.when(watchlistRepositoryMock.getList()).thenReturn(lists);

        //Act: use its own method:
        List<WatchlistItem> result = watchlistService.getWatchlistItems();

        //Assert:
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(result.get(0).getTitle(), "Star Wars");
        Assert.assertEquals(result.get(1).getTitle(), "Back to the future");
    }

    @Test
    public void addAnItem_ifItemExists_shouldReturnExistsRating() {
        //Arrange:
        WatchlistItem item1 = new WatchlistItem("Star Wars", "7.0", "H", "", 1);
        List<WatchlistItem> lists = Arrays.asList(item1);
        Mockito.when(watchlistRepositoryMock.getList()).thenReturn(lists);
        Mockito.when(movieRatingServiceMock.getMovieRating(any(String.class))).thenReturn("10");

        //Act:
        String rating = watchlistService.getWatchlistItems().get(0).getRating();

        //Assert:
        Assert.assertEquals("10", rating);
    }

}
