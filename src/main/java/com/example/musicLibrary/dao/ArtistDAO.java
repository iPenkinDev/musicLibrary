package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Artist;

import java.util.List;

public interface ArtistDAO {
    Artist createArtist(Artist artist);

    Artist getArtistById(long id);

    List<Artist> getAllArtists();

    Artist updateArtist(Artist artist);

    void deleteArtist(long id);

}