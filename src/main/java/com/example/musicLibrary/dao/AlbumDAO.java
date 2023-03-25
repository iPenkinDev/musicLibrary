package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Album;

import java.util.List;

public interface AlbumDAO {
    Album createAlbum(Album album);

    Album getAlbumById(long id);

    Album updateAlbum(Album album);

    void deleteAlbum(long id);

    List<Album> getAlbumsByArtistId(long artistId);

    Album getAlbumByArtistIdAndAlbumId(long artistId, long albumId);
}
