package com.movie.movie;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.movie.movie.Controller.MovieController;
import com.movie.movie.Entity.MovieV;
import com.movie.movie.Service.MovieService;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MovieService service;

    List<MovieV> empList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        empList = List.of(new MovieV(100, "Suits", "Jack Black", "Harvey Specter", 478000, "1hr 15min"),
                new MovieV(200, "Chuck Norris", "Mike Ross", "Bruce Lee", 582000, "2hr 15min"));
    }

    @Test
    public void getAllEmployees() throws Exception {
        when(service.findAllMovies()).thenReturn(empList);
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
}
