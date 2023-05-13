package com.example.musicLibrary.dto.response;

import com.example.musicLibrary.dto.AlbumDTO;
import lombok.Data;

import java.util.List;

@Data
public class AlbumResponse {

    private List<AlbumDTO> content;
    private int page;
    private int pageSize;
    private int totalPage;
    private long totalElement;
    private boolean isLast;
}
