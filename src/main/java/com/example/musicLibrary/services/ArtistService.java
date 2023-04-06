package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.dto.forms.ArtistForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {
    ArtistDTO createArtist(ArtistDTO artistDTO);

    ArtistDTO getArtistById(long id);

    List<ArtistDTO> getAllArtists();

    ArtistDTO updateArtist(ArtistForm artistForm);

    void deleteArtist(long id);

    ArtistDTO findArtistByName(String name);
}
