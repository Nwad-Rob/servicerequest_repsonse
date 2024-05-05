package com.movie.movie.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    @SuppressWarnings("unused")
    private static final Long serialversionUID = 1L;

    public ResourceNotFoundException (String message){
        super(message);
    }
}
