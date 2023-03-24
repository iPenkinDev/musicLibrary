package com.example.musicLibrary.dao;

import com.example.musicLibrary.models.Album;
import com.example.musicLibrary.models.Artist;

import java.util.List;

public interface AlbumDAO {
    public Album createAlbum(Album album);
    public Album getAlbumById(long id);
    public List<Album> getAllAlbums();
    public Album updateAlbum(Album album);
    public void deleteAlbum(long id);
    List<Artist> getArtistsAlbums(long id);
}
