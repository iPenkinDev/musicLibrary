package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.dto.response.ArtistResponse;
import com.example.musicLibrary.enumeration.ArtistSortBy;
import com.example.musicLibrary.enumeration.SortDirection;
import org.springframework.stereotype.Service;

@Service
public interface ArtistService {
    ArtistDTO createArtist(ArtistDTO artistDTO);

    ArtistDTO getArtistById(long id);

    ArtistResponse getAllArtistsPages(int page, int pageSize, ArtistSortBy sortBy, SortDirection sortDir);

    ArtistDTO updateArtist(ArtistDTO artistDTO, long id);

    void deleteArtist(long id);

    ArtistDTO findArtistByName(String name);
}
