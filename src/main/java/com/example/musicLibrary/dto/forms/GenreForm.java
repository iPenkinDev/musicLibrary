package com.example.musicLibrary.dto.forms;

import lombok.Data;

@Data
public class GenreForm {
    private long id;
    private long songId;
    private String title;
}
