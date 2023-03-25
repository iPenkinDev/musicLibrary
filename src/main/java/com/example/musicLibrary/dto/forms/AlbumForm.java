package com.example.musicLibrary.dto.forms;

import com.example.musicLibrary.models.Artist;
import lombok.Data;

@Data
public class AlbumForm {
    private long id;
    private long artistId;
    private String title;
    private int year;
}
