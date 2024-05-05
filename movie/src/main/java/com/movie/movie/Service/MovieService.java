package com.movie.movie.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.movie.movie.Dao.MovieRepository;
import com.movie.movie.Entity.MovieV;
import com.movie.movie.Entity.MovieV.PrimaryKeys;
import com.movie.movie.Model.Request.MovieRequest;
import com.movie.movie.Model.Response.Movie;
import com.movie.movie.Model.Response.MovieResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
    
    @Autowired
    MovieRepository movieRepo;
     
    public MovieResponse getMovies(MovieRequest request){

        List<MovieV> movlist = movieRepo.getMovieDetailsByDirectorname(request.getDirectorname(),request.getMovieid());
        

        if(movlist.isEmpty()){
            return MovieResponse.builder().build();
        }
        
        return MovieResponse.builder()
                            .movieList(movievToMovie(movlist))
                            .build();
    }

    public List<Movie> movievToMovie(List<MovieV> mov) {
        List<Movie> movieList = new ArrayList<>();
        for (MovieV x : mov) {
            Movie movie = Movie.builder()
                .movieid(x.getMovieid())
                .moviename(x.getMoviename())
                .directorname(x.getDirectorname())
                .leadingActor(x.getLeadingactor())
                .grossing(x.getGrossing())
                .duration(x.getDuration())
                .build();
            movieList.add(movie);
        }
        return movieList;
    }
    

    public List<MovieV> findAllMovies() {
        return movieRepo.findAll();
    }

    public MovieV saveMovies(@Valid MovieV e) {
        return movieRepo.save(e);
    }

    public void deleteMoviesById(PrimaryKeys movieid) {
         movieRepo.deleteById(movieid);
    }

    public List<MovieV> checkTest(String moviename) {
       return movieRepo.moviesByMovieName(moviename);
    }
}
