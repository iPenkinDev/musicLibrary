package com.example.musicLibrary.dao;

import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.models.Song;

import java.util.List;

public interface SongDAO {
    public Song createSong(Song song);

    public Song getSongById(long id);

    public List<Song> getAllSongs();

    public Song updateSong(Song song);

    public void deleteSong(long id);
}
