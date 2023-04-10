package com.example.musicLibrary.dto;

import com.example.musicLibrary.entity.Artist;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AlbumDTO {
    private long id;
    @NotNull(message = "Title cannot be null")
    private String title;
    @NotNull (message = "Genre cannot be null")
    @Min(value = 1900, message = "Year cannot be less than 1900")
    @Max(value = 2023, message = "Year cannot be greater than 2023")
    private int year;
    private Artist artist;
}
