package com.movie.movie.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.movie.Dao.MovieRepository;
import com.movie.movie.Entity.MovieV;
import com.movie.movie.Model.Request.MovieRequest;
import com.movie.movie.Model.Response.Movie;
import com.movie.movie.Model.Response.MovieResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
    
    private final MovieRepository movieRepo;
     
    private final MovieResponse movieRes;

    public MovieResponse getMovies(MovieRequest request,List<MovieV> mov){

        List<MovieV> movlist = movieRepo.getMovieDetailsByDirectorname(request.getDirectorname(),request.getMovieid());
        var movies = MovieV.builder()
                           .movieid(request.getMovieid())
                           .directorname(request.getDirectorname())
                           .build();

            movieRepo.save(movies);


        return MovieResponse.builder()
                            .movieList(movievToMovie(movlist, request))
                            .build();
    }

    public List<Movie> movievToMovie(List<MovieV> mov,MovieRequest request){
        List<MovieV> movlist = movieRepo.getMovieDetailsByDirectorname(request.getDirectorname(),request.getMovieid());
        List<Movie> movieList = new ArrayList<Movie>();
        Movie currentMovie = null;
        for (MovieV x : movlist){
            currentMovie = Movie.builder()
                                .movieid(x.getMovieid())
                                .moviename(x.getMoviename())
                                .directorname(x.getDirectorname())
                                .leadingActor(x.getLeadingactor())
                                .grossing(x.getGrossing())
                                .duration(x.getDuration())
                                .build();
            
            movieList.add(currentMovie);
        }

        return movieList;
    }
}
