package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Genre;

import java.util.List;

public interface GenreDAO {
    public Genre createGenre(Genre genre);
    public Genre getGenreById(long id);
    public List<Genre> getAllGenres();
    public void updateGenre(Genre genre, long id);
    public void deleteGenre(long id);
}
