package com.movie.movie;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.movie.Controller.MovieController;
import com.movie.movie.Entity.MovieV;
import com.movie.movie.Model.Request.MovieRequest;
import com.movie.movie.Model.Response.Movie;
import com.movie.movie.Model.Response.MovieResponse;
import com.movie.movie.Service.MovieService;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MovieService service;

    @Autowired
    ObjectMapper objectMapper;

    List<MovieV> movieList = new ArrayList<>();
    MovieResponse movieResponse; 

    @BeforeEach
    public void setUp() {
        movieList = List.of(
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
    public void getAllMovies() throws Exception {
        when(service.findAllMovies()).thenReturn(movieList);
        mockMvc.perform(get("/mov/v1/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].movieid").exists())
                .andExpect(jsonPath("$[*].moviename").exists())
                .andExpect(jsonPath("$[*].directorname").exists())
                .andExpect(jsonPath("$[*].leadingactor").exists())
                .andExpect(jsonPath("$[*].grossing").exists())
                .andExpect(jsonPath("$[*].duration").exists());
        ;

    }

    @Test
    public void testGetMovies_EmptyList() throws Exception {
        // Mock the behavior of the repository to return an empty list when findAll() is
        // called
        when(service.findAllMovies()).thenReturn(Collections.emptyList());

        // Perform the GET request to "/mov/v1"
        mockMvc.perform(get("/mov/v1"))
                .andExpect(status().isNotFound()); // Expecting a 404 Not Found response
    }

    @Test
    public void testEmptyRequest() throws Exception {
        MovieRequest emptyRequest = new MovieRequest();
        // emptyRequest fields are not set, making it an invalid request

        mockMvc.perform(post("/mov/v1/movieregister")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emptyRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateMovie_BadRequest() throws Exception {
        // Sending an invalid request body
        String invalidMovieJson = "{ \"invalidField\": \"invalidValue\" }";

        mockMvc.perform(post("/mov/v1/movieregister")
                .content(invalidMovieJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateMovie_InvalidRequestFields() throws Exception {
        // Creating a MovieRequest with invalid fields
        MovieRequest invalidRequest = new MovieRequest();
        invalidRequest.setMovieid("1"); // Invalid as it's less than 2 characters
        invalidRequest.setDirectorname("AB"); // Invalid as it's less than 3 characters

        mockMvc.perform(post("/mov/v1/movieregister")
                .content(objectMapper.writeValueAsString(invalidRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCorrectResponse() throws Exception {
        MovieRequest validRequest = new MovieRequest();
        validRequest.setDirectorname("Jack Black");
        validRequest.setMovieid("100");

        when(service.getMovies(validRequest)).thenReturn(movieResponse);

        mockMvc.perform(post("/mov/v1/movieregister")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieList").isArray())
                .andExpect(jsonPath("$.movieList[0].movieid").value("100"))
                .andExpect(jsonPath("$.movieList[0].moviename").value("Suits"))
                .andExpect(jsonPath("$.movieList[0].directorname").value("Jack Black"))
                .andExpect(jsonPath("$.movieList[0].leadingActor").value("Harvey Specter"))
                .andExpect(jsonPath("$.movieList[0].grossing").value(478000))
                .andExpect(jsonPath("$.movieList[0].duration").value("1hr 15min"));
    }

}
