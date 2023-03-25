package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Song;

import java.util.List;

public interface SongDAO {
    Song createSong(Song song);

    Song getSongById(long id);

    List<Song> getAllSongs();

    Song updateSong(Song song);

    void deleteSong(long id);
}
