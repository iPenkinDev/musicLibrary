package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.dto.response.AlbumResponse;
import com.example.musicLibrary.enumeration.AlbumSortBy;
import com.example.musicLibrary.enumeration.SortDirection;

import java.util.List;


public interface AlbumService {
    AlbumDTO createAlbum(AlbumDTO albumDTO, long artistId);

    AlbumDTO getAlbumById(long id);

    AlbumResponse getAllAlbumsPages(int page, int pageSize, AlbumSortBy sortBy, SortDirection sortDir);

    AlbumDTO updateAlbum(AlbumDTO albumDTO, long id);

    void deleteAlbum(long id);

    List<AlbumDTO> getAlbumsByArtistId(long artistId);

    AlbumDTO getAlbumByArtistIdAndAlbumId(long artistId, long albumId);

    AlbumDTO findAlbumByTitle(String title);
}
