package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;

import java.util.List;

public interface GenreDAO {
    Genre createGenre(Genre genre);

    Genre getGenreById(long id);

    List<Genre> getAllGenres();

    Genre updateGenre(Genre genre);

    void deleteGenre(long id);

    void addGenreToSong(long genreId, long songId);

    List<Song> getSongsByGenreId(long id);

}
