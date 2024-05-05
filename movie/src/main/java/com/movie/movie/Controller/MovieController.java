package com.movie.movie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.movie.movie.Dao.MovieRepository;
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
     MovieRepository movieRepo;


     
// Get all Movies

@GetMapping("/movies")
public List<MovieV> getDetails(){
     return movieRepo.findAll();
}

//Create Movies
@ResponseStatus(HttpStatus.CREATED)
@PostMapping("/movies")
public ResponseEntity<MovieV> createMovie(@RequestBody @Valid MovieV e) {
    MovieV mov =  movieRepo.save(e);
    return ResponseEntity.ok(mov);
}

//Delete Movies
@DeleteMapping("{id}")
public ResponseEntity<HttpStatus> deleteMovie(@PathVariable PrimaryKeys movieid){
     movieRepo.deleteById(movieid);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

// //Checking Request
// @PostMapping("/movieregister")
// public ResponseEntity<MovieResponse> movRegister(@RequestBody MovieRequest request,
//                                                  @RequestParam("directorname") String directorname,
//                                                  @RequestParam("movieid") long movieid ){
     
// }


}