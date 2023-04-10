package com.example.musicLibrary.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@NoArgsConstructor
public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException(String message) {
        super(message);
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    public ResponseEntity<ResponseError> handleException(ArtistNotFoundException e) {
        ResponseError error = new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
