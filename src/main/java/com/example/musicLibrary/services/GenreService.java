package com.example.musicLibrary.services;

import com.example.musicLibrary.models.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    public Genre createGenre(Genre genre);
    public Genre getGenreById(long id);
    public List<Genre> getAllGenres();
    public void updateGenre(Genre genre, long id);
    public void deleteGenre(long id);
}
