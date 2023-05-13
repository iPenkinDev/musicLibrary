package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.response.GenreResponse;
import com.example.musicLibrary.enumeration.GenreSortBy;
import com.example.musicLibrary.enumeration.SortDirection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    GenreDTO createGenre(GenreDTO genreDTO);

    GenreDTO getGenreById(long id);

    GenreResponse getAllGenresPages(int page, int pageSize, GenreSortBy sortBy, SortDirection sortDir);

    GenreDTO updateGenre(GenreDTO genreDTO, long id, long songId);

    void deleteGenre(long id);

    List<SongDTO> getAllSongsByGenreId(long genreId);

    GenreDTO findGenreByTitle(String title);
}
