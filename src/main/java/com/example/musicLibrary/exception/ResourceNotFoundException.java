package com.example.musicLibrary.exception;

import lombok.Getter;


@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final Long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));

        this.fieldValue = fieldValue;
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

}
