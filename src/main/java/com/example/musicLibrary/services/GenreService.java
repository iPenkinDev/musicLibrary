package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.GenreForm;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    GenreDTO createGenre(GenreDTO genreDTO);

    GenreDTO getGenreById(long id);

    List<GenreDTO> getAllGenres();

    GenreDTO updateGenre(GenreForm genreForm);

    void deleteGenre(long id);

    void addGenreToSong(long genreId, long songId);

    void removeGenreFromSong(long genreId, long songId);

    List<SongDTO> getSongsByGenreId(long id);
}
