package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Genre;

import java.util.List;

public interface GenreDAO {
    Genre createGenre(Genre genre);

    Genre getGenreById(long id);

    List<Genre> getAllGenres();

    Genre updateGenre(Genre genre);

    void deleteGenre(long id);

    void addSongToGenre(long genreId, long songId);

    void removeSongToGenre(long genreId, long songId);

    List<Genre> getGenresBySongId(long songId);
}
