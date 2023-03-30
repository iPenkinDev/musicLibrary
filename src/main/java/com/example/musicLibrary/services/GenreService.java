package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.GenreDTO;
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

    void addSongToGenre(long genreId, long songId);

    void removeSongToGenre(long genreId, long songId);

    List<GenreDTO> getGenresBySongId(long id);
}
