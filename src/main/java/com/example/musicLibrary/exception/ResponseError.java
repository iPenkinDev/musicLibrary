package com.example.musicLibrary.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseError {

    private int status;
    private String message;

    public ResponseError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}