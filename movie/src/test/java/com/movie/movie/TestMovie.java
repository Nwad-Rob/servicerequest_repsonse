package com.movie.movie;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.movie.movie.Dao.MovieSearchDao;
import com.movie.movie.Entity.MovieV;
import com.movie.movie.Model.Request.MovieRequest;
import com.movie.movie.Model.Response.MovieResponse;
import com.movie.movie.Service.MovieService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

@Slf4j
public class TestMovie {

    //This is for testing it with connection to the Organization DB
    //What to test for: All fields, empty fields and Bad request


    @BeforeClass
    public static void setup(){

    }

    @Mocked EntityManager mockedServiceEm;
    @SuppressWarnings("rawtypes")
    @Mocked TypedQuery typedQuery;
    @Mocked CriteriaQuery<MovieV> criteriaQuery;

    /*
     * Mock up MovieList
     */
    @SuppressWarnings("unused")
    private void mockupSetMovieListMethod(){
        new MockUp<MovieSearchDao>(){
             @Mock List<MovieV> findAllByCriteria(MovieRequest movieRequest){
                List<MovieV>movieVList = new ArrayList<>();

                return movieVList;
             };
        };
    }

    @Test
        /*
         * Test all fields in response
         * @throws Exception
         */
        public void testAllFields() throws Exception{
            log.info("testmovies >> testAllFields invoked..");
            
            MovieRequest movieRequest = new MovieRequest();
            movieRequest.setMovieid(100);
            movieRequest.setDirectorname("Ben Afleck");
            MovieResponse movieResponse = MovieResponse.builder().build();
            MovieService service = new MovieService();
            MovieSearchDao dao = new MovieSearchDao();

            new Expectations(){{
                mockedServiceEm.createQuery(criteriaQuery);
                result = typedQuery;
            }};

            new Expectations(){{
                typedQuery.getResultList();
                result = fins
            }}

        }

}
