package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;

import java.util.List;

public interface SongDAO {
    Song createSong(Song song);

    Song getSongById(long id);

    List<Song> getAllSongs();

    Song updateSong(Song song);

    void deleteSong(long id);

    List<Song> getSongsByAlbumId(long albumId);

    Song getSongByAlbumIdAndSongId(long albumId, long songId);

    List<Song> getSongsByArtistId(long artistId);

    Song getSongByArtistIdAndSongId(long artistId, long songId);

    void deleteSongByGenreId(long genreId);

    List<Song> getAllSongsByGenreId(long genreId);

    List<Genre> getAllGenresBySongId(long songId);


}
