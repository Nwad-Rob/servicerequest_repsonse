package com.movie.movie.Model.Response;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Component
@Data
@Builder
@JsonPropertyOrder({ "duration","moviename", "grossing", "leadingActor"})
public class MovieResponse {
    private List<Map<String, Object>> movieList;
    //private List<Movie> movieList; if you want to use the other methods
}
