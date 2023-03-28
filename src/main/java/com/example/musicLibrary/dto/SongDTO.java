package com.example.musicLibrary.dto;

import com.example.musicLibrary.entity.Album;
import com.example.musicLibrary.entity.Genre;
import lombok.Data;

import java.util.List;

@Data
public class SongDTO {
    private long id;
    private String title;
    private int year;
    private Album album;
    private List<Genre> genres;
}
