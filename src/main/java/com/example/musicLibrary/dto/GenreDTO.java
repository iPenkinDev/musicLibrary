package com.example.musicLibrary.dto;

import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.models.Song;
import lombok.Data;

import java.util.List;

@Data
public class GenreDTO {
    private long id;
    private String title;
    private List<Song> songs;
    private Artist artistGenres;
}
