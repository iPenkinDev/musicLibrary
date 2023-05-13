package com.example.musicLibrary.dto.response;

import com.example.musicLibrary.dto.GenreDTO;
import lombok.Data;

import java.util.List;

@Data
public class GenreResponse {

    private List<GenreDTO> content;
    private int page;
    private int pageSize;
    private int totalPage;
    private long totalElement;
    private boolean isLast;
}
