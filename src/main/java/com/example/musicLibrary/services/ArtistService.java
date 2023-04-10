package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.ArtistDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {
    ArtistDTO createArtist(ArtistDTO artistDTO);

    ArtistDTO getArtistById(long id);

    List<ArtistDTO> getAllArtists();

    ArtistDTO updateArtist(ArtistDTO artistDTO, long id);

    void deleteArtist(long id);

    ArtistDTO findArtistByName(String name);
}
