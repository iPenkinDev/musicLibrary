package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.GenreForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    GenreDTO createGenre(GenreDTO genreDTO, long songId);

    GenreDTO getGenreById(long id);

    List<GenreDTO> getAllGenres();

    GenreDTO updateGenre(GenreForm genreForm);

    void deleteGenre(long id);

    void deleteGenreBySongId(long songId);

    List<GenreDTO> getAllGenresBySongId(long songId);

    List<SongDTO> getAllSongsByGenreId(long genreId);

    GenreDTO findGenreByTitle(String title);
}
