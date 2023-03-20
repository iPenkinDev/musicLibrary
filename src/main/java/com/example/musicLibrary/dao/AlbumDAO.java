package com.example.musicLibrary.dao;

import com.example.musicLibrary.models.Album;

import java.util.List;

public interface AlbumDAO {
    public Album createAlbum(Album album);
    public Album getAlbumById(long id);
    public List<Album> getAllAlbums();
    public void updateAlbum(Album album, long id);
    public void deleteAlbum(long id);
}
