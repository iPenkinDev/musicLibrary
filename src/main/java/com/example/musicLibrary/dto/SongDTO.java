package com.example.musicLibrary.dto;

import com.example.musicLibrary.entity.Album;
import com.example.musicLibrary.entity.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SongDTO {
    private long id;
    @NotNull(message = "Title cannot be null")
    private String title;
    @NotNull(message = "Year cannot be null")
    @Min(value = 1900, message = "Year must be in range 1900-2023")
    @Max(value = 2023, message = "Year must be in range 1900-2023")
    private int year;
    private Album album;
    private List<Genre> genres;
}
