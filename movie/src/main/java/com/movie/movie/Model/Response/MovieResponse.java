package com.movie.movie.Model.Response;

import java.util.List;

import com.movie.movie.Entity.Movie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponse {
    private List<Movie> movieList;
    private ErrorResponse errorResponse;
}
