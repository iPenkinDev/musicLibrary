package com.example.musicLibrary.dao;

import com.example.musicLibrary.models.Artist;

import java.util.List;

public interface ArtistDAO {
    public Artist createArtist(Artist artist);
    public Artist getArtistById(long id);
    public List<Artist> getAllArtists();
    public Artist updateArtist(Artist artist);
    public void deleteArtist(long id);
}