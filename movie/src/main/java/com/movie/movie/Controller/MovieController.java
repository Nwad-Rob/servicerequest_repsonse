package com.movie.movie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.movie.movie.Entity.MovieV;
import com.movie.movie.Entity.MovieV.PrimaryKeys;
import com.movie.movie.Model.Request.MovieRequest;
import com.movie.movie.Model.Response.MovieResponse;
import com.movie.movie.Service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mov/v1")
public class MovieController {
    @Autowired
     MovieService service;
     
// Get all Movies

@ResponseStatus(HttpStatus.OK)
@GetMapping("/movies")
public List<MovieV> getDetails(){
     return service.findAllMovies();
}

//Create Movies
@ResponseStatus(HttpStatus.CREATED)
@PostMapping("/movies")
public ResponseEntity<MovieV> createMovie(@RequestBody @Valid MovieV e) {
    MovieV mov =  service.saveMovies(e);
    return ResponseEntity.ok(mov);
}

//Delete Movies
@DeleteMapping("{id}")
public ResponseEntity<HttpStatus> deleteMovie(@PathVariable PrimaryKeys movieid){
     service.deleteMoviesById(movieid);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@PostMapping("/moviebymoviename")
public ResponseEntity<List<MovieV>> check(@RequestBody @RequestParam("moviename") String moviename){
     return ResponseEntity.ok(service.checkTest(moviename));
}



//Checking Request
@PostMapping("/movieregister")
public ResponseEntity<MovieResponse> movRegister(@RequestBody MovieRequest request){
               System.out.println("Grabbing List");
               System.out.println(service.getMovies(request));
          return ResponseEntity.ok(service.getMovies(request));
}

@PostMapping("/moviesearch")
public ResponseEntity<MovieResponse> movSearch(@RequestBody MovieRequest request){
               System.out.println("Grabbing List");
               System.out.println(service.getMoviesWhereMovienameLike(request));
          return ResponseEntity.ok(service.getMoviesWhereMovienameLike(request));
}


}