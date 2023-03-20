package com.example.musicLibrary.dao;

import com.example.musicLibrary.models.Song;

import java.util.List;

public interface SongDAO {
    public Song createSong(Song song);
    public Song getSongById(long id);
    public List<Song> getAllSongs();
    public void updateSong(Song song, long id);
    public void deleteSong(long id);
}
