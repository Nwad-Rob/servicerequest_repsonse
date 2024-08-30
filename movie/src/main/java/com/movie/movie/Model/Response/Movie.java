package com.movie.movie.Model.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private String movieid;
    private String moviename;
    private String directorname;
    private String leadingActor;
    private int grossing;
    private String duration;
}
