package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.models.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {
    public Artist createArtist(Artist artist);
    public Artist getArtistById(long id);
    public List<Artist> getAllArtists();
    public ArtistDTO updateArtist(ArtistDTO artistDTO, long id);
    public void deleteArtist(long id);
}
