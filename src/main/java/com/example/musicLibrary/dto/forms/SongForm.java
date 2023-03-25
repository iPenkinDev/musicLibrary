package com.example.musicLibrary.dto.forms;

import com.example.musicLibrary.entity.Album;
import lombok.Data;

@Data
public class SongForm {
    private long id;
    private long albumId;
    private String title;
    private int year;
}
