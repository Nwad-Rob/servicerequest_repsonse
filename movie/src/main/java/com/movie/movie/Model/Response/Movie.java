package com.movie.movie.Model.Response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
    private long movieid;
    private String moviename;
    private String directorname;
    private String leadingActor;
    private int grossing;
    private String duration;
}
