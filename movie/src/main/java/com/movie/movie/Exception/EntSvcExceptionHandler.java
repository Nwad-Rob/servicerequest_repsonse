package com.movie.movie.Exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.web.ErrorResponse;

import com.movie.movie.Model.Request.MovieRequest;
import com.movie.movie.Model.Response.MovieResponse;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class EntSvcExceptionHandler {
    
    static MovieRequest movieRequest;

    public static MovieRequest movieRequest(){
        return movieRequest;
    }

    public static void setMovieRequest(MovieRequest movieRequest){
        EntSvcExceptionHandler.movieRequest = movieRequest;
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception>{

        @Override
        public Response toResponse(Exception exception){
            int code = 500;
            if (exception instanceof WebApplicationException){
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            Response res = Response.status(code).
                entity(ErrorResponse.build)
        }
    }

}
