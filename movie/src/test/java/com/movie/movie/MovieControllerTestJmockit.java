package com.movie.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * THIS IS A TEST CLASS TO SEE THE CODE IN JMOCKIT
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

// import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.movie.Controller.MovieController;
import com.movie.movie.Entity.MovieV;
import com.movie.movie.Model.Request.MovieRequest;
import com.movie.movie.Model.Response.Movie;
import com.movie.movie.Model.Response.MovieResponse;
import com.movie.movie.Service.MovieService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
// import mockit.integration.junit5.JMockit;

@ExtendWith(JMockit.class)
public class MovieControllerTestJmockit {

    @Tested
    MovieController movieController;

    @Injectable
    MovieService service;

    @Inject
    ObjectMapper objectMapper;

    List<MovieV> movieList = new ArrayList<>();
    MovieResponse movieResponse;

    @BeforeEach
    public void setUp() {
        movieList = List.of(new MovieV("100", "Suits", "Jack Black", "Harvey Specter", 478000, "1hr 15min"),
                new MovieV("200", "Chuck Norris", "Mike Ross", "Bruce Lee", 582000, "2hr 15min"));
        List<Movie> movList = List.of(
                Movie.builder()
                        .movieid("100")
                        .moviename("Suits")
                        .directorname("Jack Black")
                        .leadingActor("Harvey Specter")
                        .grossing(478000)
                        .duration("1hr 15min")
                        .build(),
                Movie.builder()
                        .movieid("200")
                        .moviename("Chuck Norris")
                        .directorname("Mike Ross")
                        .leadingActor("Bruce Lee")
                        .grossing(582000)
                        .duration("2hr 15min")
                        .build()
        );
        movieResponse = MovieResponse.builder().movieList(movList).build();
    }

    @Test
    public void getAllMovies(@Mocked MovieService service) {
        new Expectations() {{
            service.findAllMovies();
            result = movieList;
        }};

        List<MovieV> result = movieController.getAllMovies();
        assertEquals(2, result.size());
        assertEquals("Suits", result.get(0).getMoviename());
    }

    @Test
    public void testGetMovies_EmptyList(@Mocked MovieService service) {
        new Expectations() {{
            service.findAllMovies();
            result = Collections.emptyList();
        }};

        List<MovieV> result = movieController.getAllMovies();
        assertEquals(0, result.size());
    }

    @Test
    public void testEmptyRequest() {
        MovieRequest emptyRequest = new MovieRequest();

        new Expectations() {{
            service.getMovies(emptyRequest);
            result = movieResponse;
        }};

        MovieResponse result = movieController.createMovie(emptyRequest);
        assertEquals(movieResponse, result);
    }

    @Test
    public void testCreateMovie_BadRequest() {
        String invalidMovieJson = "{ \"invalidField\": \"invalidValue\" }";

        new Expectations() {{
            service.getMovies((MovieRequest) any);
            result = null;
        }};

        MovieRequest invalidRequest = new MovieRequest();
        MovieResponse result = movieController.createMovie(invalidRequest);
        assertEquals(null, result);
    }

    @Test
    public void testCreateMovie_InvalidRequestFields() {
        MovieRequest invalidRequest = new MovieRequest();
        invalidRequest.setMovieid("1");
        invalidRequest.setDirectorname("AB");

        new Expectations() {{
            service.getMovies(invalidRequest);
            result = null;
        }};

        MovieResponse result = movieController.createMovie(invalidRequest);
        assertEquals(null, result);
    }

    @Test
    public void testCorrectResponse() {
        MovieRequest validRequest = new MovieRequest();
        validRequest.setDirectorname("Jack Black");
        validRequest.setMovieid("100");

        new Expectations() {{
            service.getMovies(validRequest);
            result = movieResponse;
        }};

        MovieResponse result = movieController.createMovie(validRequest);
        assertEquals(movieResponse, result);
        assertEquals("Suits", result.getMovieList().get(0).getMoviename());
    }

}
