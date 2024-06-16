package com.movie.movie;

import java.util.List;

import com.movie.movie.Entity.MovieV;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement
public class MovieMockData {
    
    private List<MovieV> movieList;
}
