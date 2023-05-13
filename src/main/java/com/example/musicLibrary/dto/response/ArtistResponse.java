package com.example.musicLibrary.dto.response;

import com.example.musicLibrary.dto.ArtistDTO;
import lombok.Data;

import java.util.List;

@Data
public class ArtistResponse {

    private List<ArtistDTO> content;
    private int page;
    private int pageSize;
    private int totalPage;
    private long totalElement;
    private boolean isLast;
}
