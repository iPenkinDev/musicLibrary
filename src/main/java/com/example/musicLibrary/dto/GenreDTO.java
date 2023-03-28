package com.example.musicLibrary.dto;

import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.entity.Song;
import lombok.Data;

import java.util.List;

@Data
public class GenreDTO {
    private long id;
    private String title;
    private List<Song> songs;
}
