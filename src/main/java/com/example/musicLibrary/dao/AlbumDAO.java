package com.example.musicLibrary.dao;

import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.models.Album;
import com.example.musicLibrary.models.Artist;

import java.util.List;

public interface AlbumDAO {
    public Album createAlbum(Album album);
    public Album getAlbumById(long id);
    public Album updateAlbum(Album album);
    public void deleteAlbum(long id);
    List<Album> getAlbumsByArtistId(long artistId);
    Album getAlbumByArtistIdAndAlbumId(long artistId, long albumId);
}
