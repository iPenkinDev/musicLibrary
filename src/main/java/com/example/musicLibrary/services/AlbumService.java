package com.example.musicLibrary.services;

import com.example.musicLibrary.models.Album;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AlbumService {
    public Album createAlbum(Album album);
    public Album getAlbumById(long id);
    public List<Album> getAllAlbums();
    public void updateAlbum(Album album, long id);
    public void deleteAlbum(long id);
}
