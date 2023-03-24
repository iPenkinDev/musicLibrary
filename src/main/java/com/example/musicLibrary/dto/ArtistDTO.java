package com.example.musicLibrary.dto;

import com.example.musicLibrary.models.Album;
import com.example.musicLibrary.models.Song;
import lombok.Data;

import java.util.List;

@Data
public class ArtistDTO {
    private long id;
    private String name;
    private String country;
    private int dateOfBirth;
    private List<Album> albums;
}
