package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.SongForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {
    SongDTO createSong(SongDTO songDTO, long albumId);

    SongDTO getSongById(long id);

    List<SongDTO> getAllSongs();

    SongDTO updateSong(SongForm songForm);

    void deleteSong(long id);

    List<SongDTO> getSongsByAlbumId(long albumId);

    SongDTO getSongByAlbumIdAndSongId(long albumId, long songId);

    List<SongDTO> getSongsByArtistId(long artistId);

    SongDTO getSongByArtistIdAndSongId(long artistId, long songId);

    void deleteSongByGenreId(long genreId);

    List<SongDTO> getAllSongsByGenreId(long genreId);

    SongDTO findSongByTitle(String title);
}
