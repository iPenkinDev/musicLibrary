package com.example.musicLibrary.dto;

import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.entity.Genre;
import lombok.Data;

import java.util.List;

@Data
public class SongDTO {
    private long id;
    private String title;
    private int year;
    private Artist artistSongs;
    private List<Genre> genres;
}
