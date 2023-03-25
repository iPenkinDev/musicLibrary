package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.dto.forms.AlbumForm;
import com.example.musicLibrary.models.Album;
import com.example.musicLibrary.models.Artist;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AlbumService {
    public AlbumDTO createAlbum(AlbumDTO albumDTO, long artistId);
    public AlbumDTO getAlbumById(long id);
    public AlbumDTO updateAlbum(AlbumForm albumForm);
    public void deleteAlbum(long id);
    List<AlbumDTO> getAlbumsByArtistId(long artistId);
    AlbumDTO getAlbumByArtistIdAndAlbumId(long artistId, long albumId);
}
