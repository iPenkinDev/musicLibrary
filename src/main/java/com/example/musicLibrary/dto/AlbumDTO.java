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
    @Min(value = 4, message = "Interval must be in range 4")
    @Max(value = 4, message = "Interval must be in range 4")
    private int year;
    private Artist artist;
}
