package com.movie.movie.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.movie.movie.Entity.MovieV;
import com.movie.movie.Model.Request.MovieRequest;
import com.movie.movie.Model.Response.Movie;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MovieSearchDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<MovieV> findAllBySimpleQuery(
            long movieid,
            String moviename,
            String directorname,
            String leadingactor,
            int grossing,
            String duration) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovieV> criteriaQuery = criteriaBuilder.createQuery(MovieV.class);

        // Select * From Movies
        Root<MovieV> root = criteriaQuery.from(MovieV.class);

        // prepare the WHERE clause
        // WHERE moviename like '%One%'
        Predicate movienamePredicate = criteriaBuilder.like(root.get("moviename"), "%" + moviename + "%");
        Predicate leadingactorPredicate = criteriaBuilder.like(root.get("moviename"), "%" + leadingactor + "%");
        Predicate durationPredicate = criteriaBuilder.like(root.get("moviename"), "%" + duration + "%");

        // Using OR and AND Operations
        Predicate movienameOrDurationOrLeadingactorPredicate = criteriaBuilder.or(
            movienamePredicate, 
            leadingactorPredicate, 
            durationPredicate);

        //Final Query ==> select * from Movies where moviename like %One%
        //or duration like %One%
                //This is the predicate for writing AND
        Predicate movienameAndDurationAndLeadingactorPredicate = criteriaBuilder.and(
                movienameOrDurationOrLeadingactorPredicate,
                movienamePredicate);

        criteriaQuery.where(movienameOrDurationOrLeadingactorPredicate);

        TypedQuery<MovieV> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();

    }

    public List<MovieV> findByReplacement(MovieRequest request){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovieV> criteriaQuery = criteriaBuilder.createQuery(MovieV.class);

        //Select from Movies
        Root<MovieV> root = criteriaQuery.from(MovieV.class);
        
        
        //Create Predicates
        Predicate movieIdPredicate = criteriaBuilder.equal(root.get("movieid"), request.getMovieid());
        Predicate directorNamePredicate = criteriaBuilder.equal(root.get("directorname"), request.getDirectorname());
        
        
        //Combine Predicates
        criteriaQuery.where(criteriaBuilder.and(movieIdPredicate,directorNamePredicate));

        TypedQuery<MovieV> typedQuery = entityManager.createQuery(criteriaQuery);
        
        return typedQuery.getResultList();

    }


    public List<MovieV> findAllByCriteria(MovieRequest request){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovieV> criteriaQuery = criteriaBuilder.createQuery(MovieV.class);

        List<Predicate> predicates = new ArrayList<>();

        //select from Movies

        Root<MovieV> root = criteriaQuery.from(MovieV.class);
        if (request.getDirectorname() != null){
            Predicate directornamePredicate = criteriaBuilder
                .like(root.get("directorname"), "%" + request.getDirectorname()+"%" );
                predicates.add(directornamePredicate);
        }

        if (request.getMovieid() != null) {
            Predicate movieidPredicate = criteriaBuilder
                .equal(root.get("movieid"), request.getMovieid());
            predicates.add(movieidPredicate);
        }
        

        
        criteriaQuery.where(
            criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<MovieV> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();

    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager){
        this.entityManager =  entityManager;
    }

    

}
