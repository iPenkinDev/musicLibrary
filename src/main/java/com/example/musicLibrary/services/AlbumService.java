package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.dto.forms.AlbumForm;

import java.util.List;


public interface AlbumService {
    AlbumDTO createAlbum(AlbumDTO albumDTO, long artistId);

    AlbumDTO getAlbumById(long id);

    List<AlbumDTO> getAllAlbums();

    AlbumDTO updateAlbum(AlbumForm albumForm);

    void deleteAlbum(long id);

    List<AlbumDTO> getAlbumsByArtistId(long artistId);

    AlbumDTO getAlbumByArtistIdAndAlbumId(long artistId, long albumId);
}
