package com.movie.movie;

import com.movie.movie.Service.MovieService;
import com.movie.movie.Dao.MovieSearchDao;
import com.movie.movie.Model.Request.MovieRequest;
import com.movie.movie.Model.Response.MovieResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTestx {

    @InjectMocks
    private MovieService service;

    @Mock
    private MovieSearchDao appDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMoviesReplaced() {
        List<Map<String, Object>> expectedList = List.of(
            Map.of(
                "moviename", "Suits",
                "duration", "1hr 15min",
                "grossing", 478000,
                "leadingActor", "Harvey Specter"
            )
        );

        MovieRequest request = new MovieRequest();
        when(appDao.findByReplacement(request)).thenReturn(Collections.emptyList());

        MovieResponse response = service.getMoviesReplaced(request);

        assertNotNull(response);
        assertNotNull(response.getMovieList());
        assertEquals(expectedList, response.getMovieList());

        verify(appDao, times(1)).findByReplacement(request);
    }

    @Test
    public void testEmptyRequest() {
        MovieRequest emptyRequest = new MovieRequest();
        when(appDao.findByReplacement(emptyRequest)).thenReturn(Collections.emptyList());

        MovieResponse response = service.getMoviesReplaced(emptyRequest);

        assertNotNull(response);
        assertNotNull(response.getMovieList()); // Ensure this is not null
        assertEquals(0, response.getMovieList().size());

        verify(appDao, times(1)).findByReplacement(emptyRequest);
    }
}
