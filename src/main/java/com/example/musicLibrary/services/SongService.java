package com.example.musicLibrary.services;

import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.models.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {
    public Song createSong(Song song);
    public Song getSongById(long id);
    public List<Song> getAllSongs();
    public SongDTO updateSong(SongDTO songDTO, long id);
    public void deleteSong(long id);
    List<Artist> getSongOwner(long id);
}
