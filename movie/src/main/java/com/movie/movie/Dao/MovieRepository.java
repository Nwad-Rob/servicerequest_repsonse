package com.movie.movie.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.movie.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Movie.PrimaryKeys>{
    
}
