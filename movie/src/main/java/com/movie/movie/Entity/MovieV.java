package com.movie.movie.Entity;

import java.io.Serializable;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Immutable
@Table(name ="Movies")
@NoArgsConstructor
@IdClass(MovieV.PrimaryKeys.class)
public class MovieV implements Serializable {
    
 
    @SuppressWarnings("unused")
    private static final long serialversionUID = 1l;

    @Data
    public static class PrimaryKeys implements Serializable{
        private String directorname;
        private long movieid;
    }

    @Id
    private long movieid;
    private String moviename;
    @Id
    private String directorname;
    private String leadingactor;
    private int grossing;
    private String duration;

    public MovieV(long movieid, String moviename, String directorname, String leadingactor, int grossing, String duration) {
        movieid = this.movieid;
        moviename =  this.moviename;
        directorname = this.directorname;
        leadingactor = this.leadingactor;
        grossing = this.grossing;
        duration = this.duration;
        
    }
}
