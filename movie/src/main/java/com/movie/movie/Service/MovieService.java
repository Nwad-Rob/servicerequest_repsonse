package com.movie.movie.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.movie.movie.Dao.MovieRepository;
import com.movie.movie.Entity.MovieV;
import com.movie.movie.Entity.MovieV.PrimaryKeys;
import com.movie.movie.Model.Request.MovieRequest;

import com.movie.movie.Model.Response.Movie;
import com.movie.movie.Model.Response.MovieResponse;
import com.movie.movie.Dao.MovieSearchDao;

import jakarta.validation.Valid;
import javax.inject.Inject;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
    
    @Autowired
    MovieRepository movieRepo;

    @Inject
    MovieSearchDao appDao;
     
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

    public MovieResponse getMoviesWhereMovienameLike(MovieRequest movieRequest){
        List <MovieV> movieList = null;
        movieList = appDao.findAllByCriteria(movieRequest);

        if(movieList.isEmpty()){
            return MovieResponse.builder().build();
        }

        return MovieResponse.builder()
        .movieList(movievToMovie(movieList))
        .build();
    }

    public MovieResponse getMoviesReplaced(MovieRequest movieRequest){ 

        List <MovieV> movieList = appDao.findByReplacement(movieRequest);
        System.out.println(movieList);

        if (movieList == null){
            movieList = Collections.emptyList();
        }

        if(movieList.isEmpty()){
            return MovieResponse.builder().build();
        }

        
        List<Map<String, Object>> finalList = movieList.stream().map(movie -> {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("moviename", movie.getMoviename());
        dto.put("duration", movie.getDuration());
        dto.put("grossing", movie.getGrossing());

        // Conditionally add the leading actor field
        if (!"Zoro Juro".equalsIgnoreCase(movie.getLeadingactor())) {
            dto.put("leadingActor", movie.getLeadingactor());
        }

        return dto;
    }).collect(Collectors.toList());

    return MovieResponse.builder()
        .movieList(finalList)
       .build();
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
