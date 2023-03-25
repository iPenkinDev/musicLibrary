package com.example.musicLibrary.dto;

import com.example.musicLibrary.entity.Album;
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
