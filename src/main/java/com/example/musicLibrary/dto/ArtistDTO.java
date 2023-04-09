package com.example.musicLibrary.dto;

import com.example.musicLibrary.entity.Album;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class ArtistDTO {
    private long id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull (message = "Country cannot be null")
    private String country;
    @NotNull(message = "Date of birth cannot be null")
    @Min(value = 1900, message = "Date of birth cannot be less than 1900")
    @Max(value = 2023, message = "Date of birth cannot be greater than 2023")
    private int yearOfBirth;
    private List<Album> albums;
}
