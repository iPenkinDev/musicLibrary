package com.example.musicLibrary.exception;

import jakarta.persistence.NoResultException;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@NoArgsConstructor
public class ApplicationException extends RuntimeException {

    public ApplicationException(Exception e) {
        super(e);
    }

    public ApplicationException(String message) {
        super(message);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ResponseError> handleException(ApplicationException e) {
        ResponseError error = new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ResponseError> handleException(NoResultException e) {
        ResponseError error = new ResponseError(HttpStatus.CONFLICT.value(), "No result found");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ResponseError error = new ResponseError(HttpStatus.BAD_REQUEST.value(), "Validation error");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> handleException(HttpMessageNotReadableException e) {
        ResponseError error = new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
