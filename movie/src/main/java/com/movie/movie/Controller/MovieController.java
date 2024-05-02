package com.movie.movie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.movie.movie.Dao.MovieRepository;
import com.movie.movie.Entity.Movie;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mov/v1")
public class MovieController {
    @Autowired
     MovieRepository movieRepo;
     
// Get all Movies

@GetMapping("/movies")
public List<Movie> getDetails(){
     return movieRepo.findAll();
}

//Create Movies
@ResponseStatus(HttpStatus.CREATED)
@PostMapping("/movies")
public ResponseEntity<Movie> createMovie(@RequestBody @Valid Movie e) {
    Movie emp =  movieRepo.save(e);
    return ResponseEntity.ok(emp);
}

//Delete Movies
@DeleteMapping("{id}")
public ResponseEntity<HttpStatus> deleteMovie(@PathVariable long id){
     movieRepo.deleteById(id);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

//Update Movies

@PutMapping("Movies/{id}")
public ResponseEntity<Movie> updateMovie(@PathVariable long id, @RequestBody @Valid Movie MovieDetails){
     Movie e = movieRepo.findById(id)
     .orElseThrow(() -> new ResourceNotFoundException("Movie does not exist with Id" + id));

     e.setFirstName(MovieDetails.getFirstName());
     e.setLastName(MovieDetails.getLastName());
     e.setEmail(MovieDetails.getEmail());

     Movie updatedMovie = movieRepo.save(e);
     return ResponseEntity.ok(updatedMovie);
}

}