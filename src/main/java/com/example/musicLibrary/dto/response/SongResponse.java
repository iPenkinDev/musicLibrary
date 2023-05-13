package com.example.musicLibrary.dto.response;

import com.example.musicLibrary.dto.SongDTO;
import lombok.Data;

import java.util.List;

@Data
public class SongResponse {

    private List<SongDTO> content;
    private int page;
    private int pageSize;
    private int totalPage;
    private long totalElement;
    private boolean isLast;
}
