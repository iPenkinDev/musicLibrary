package com.example.musicLibrary.dto;

import com.example.musicLibrary.entity.Song;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class GenreDTO {
    private long id;
    @NotNull(message = "Title cannot be null")
    private String title;
    @JsonIgnore
    private List<Song> songs;
}
