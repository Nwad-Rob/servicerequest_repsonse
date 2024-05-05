package com.movie.movie.Model.Response;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;

@Component
@Data
@Builder
public class MovieResponse {
    private List<Movie> movieList;
}
