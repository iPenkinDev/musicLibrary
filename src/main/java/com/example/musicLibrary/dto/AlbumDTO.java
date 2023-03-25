package com.example.musicLibrary.dto;

import com.example.musicLibrary.models.Artist;
import lombok.Data;

@Data
public class AlbumDTO {
    private long id;
    private String title;
    private int year;
    private Artist artistAlbums;


}
