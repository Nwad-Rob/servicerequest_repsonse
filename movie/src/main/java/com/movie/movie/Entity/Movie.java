package com.movie.movie.Entity;

import java.io.Serializable;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Immutable
@Table(name ="Movies")
public class Movie implements Serializable {
    
    private static final long serialversionUID = 1l;

    @Data
    public static class PrimaryKeys implements Serializable{
        private long movieid;
        private String directorname;
    }

    @Id
    private long movieid;
    private String moviename;
    @Id
    private String directorname;
    private String leadingActor;
    private int grossing;
    private String duration;
}
