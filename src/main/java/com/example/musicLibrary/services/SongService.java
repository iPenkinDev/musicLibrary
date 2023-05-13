package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.response.SongResponse;
import com.example.musicLibrary.enumeration.SongSortBy;
import com.example.musicLibrary.enumeration.SortDirection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {
    SongDTO createSong(SongDTO songDTO, long albumId, long genreId);

    SongDTO getSongById(long id);

    SongResponse getAllSongsPages(int page, int pageSize, SongSortBy sortBy, SortDirection sortDir);

    SongDTO updateSong(SongDTO songDTO, long id, long albumId, long genreId);

    void deleteSong(long id);

    List<SongDTO> getSongsByAlbumId(long albumId);

    SongDTO getSongByAlbumIdAndSongId(long albumId, long songId);

    List<SongDTO> getSongsByArtistId(long artistId);

    SongDTO getSongByArtistIdAndSongId(long artistId, long songId);

    void deleteSongByGenreId(long genreId);

    List<SongDTO> getAllSongsByGenreId(long genreId);

    SongDTO findSongByTitle(String title);
}
