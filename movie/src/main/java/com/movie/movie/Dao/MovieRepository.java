package com.movie.movie.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.movie.Entity.MovieV;

@Repository
public interface MovieRepository extends JpaRepository<MovieV, MovieV.PrimaryKeys> {

    Optional<MovieV> findByDirectorname(String directorname);

    Optional<MovieV> findByLeadingactor(String leadingactor);

    Optional<MovieV> findByMoviename(String Moviename);

    Optional<MovieV> findByMovieid(long movieid);

    Optional<MovieV> findByGrossing(int grossing);

    Optional<MovieV> findByDuration(int duration);

    @Query(value = "SELECT * FROM Movies WHERE directorname = :directorname AND movieid = :movieid", nativeQuery = true)
    List<MovieV> moviesByDirector(@Param("directorname") String directorname,@Param("movieid") long movieid);

    default List<MovieV> getMovieDetailsByDirectorname(@RequestParam("directorname") String directorname, @RequestParam("moveiid") long movieid){
          if (directorname != null && !directorname.trim().isEmpty() && movieid != 0) {
            return moviesByDirector(directorname,movieid);
        }else{
            return null;
        }
    }
}
