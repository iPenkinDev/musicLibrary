package com.example.musicLibrary.services;

import com.example.musicLibrary.models.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {
    public Song createSong(Song song);
    public Song getSongById(long id);
    public List<Song> getAllSongs();
    public void updateSong(Song song, long id);
    public void deleteSong(long id);
}
