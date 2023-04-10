package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.AlbumDTO;

import java.util.List;


public interface AlbumService {
    AlbumDTO createAlbum(AlbumDTO albumDTO);

    AlbumDTO getAlbumById(long id);

    List<AlbumDTO> getAllAlbums();

    AlbumDTO updateAlbum(AlbumDTO albumDTO, long id);

    void deleteAlbum(long id);

    List<AlbumDTO> getAlbumsByArtistId(long artistId);

    AlbumDTO getAlbumByArtistIdAndAlbumId(long artistId, long albumId);

    AlbumDTO findAlbumByTitle(String title);
}
