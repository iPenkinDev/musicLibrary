package com.example.musicLibrary.dto.forms;

import lombok.Data;

@Data
public class SongForm {
    private long id;
    private long albumId;
    private long genreId;
    private String title;
    private int year;
}
