package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.entity.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {
    Artist createArtist(Artist artist);

    Artist getArtistById(long id);

    List<Artist> getAllArtists();

    ArtistDTO updateArtist(ArtistDTO artistDTO, long id);

    void deleteArtist(long id);

}
