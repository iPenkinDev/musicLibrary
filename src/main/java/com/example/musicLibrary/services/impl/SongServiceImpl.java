package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.ArtistDAO;
import com.example.musicLibrary.dao.SongDAO;
import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.models.Song;
import com.example.musicLibrary.services.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private SongDAO songDAO;
    private ArtistDAO artistDAO;
    private ModelMapper modelMapper;

    @Autowired
    public SongServiceImpl(SongDAO songDAO, ArtistDAO artistDAO, ModelMapper modelMapper) {
        this.songDAO = songDAO;
        this.artistDAO = artistDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public Song createSong(Song song) {
        return songDAO.createSong(song);
    }

    @Override
    public Song getSongById(long id) {
        return songDAO.getSongById(id);
    }

    @Override
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }

    @Override
    public SongDTO updateSong(SongDTO songDTO, long id) {
        Song songUpdate = songDAO.getSongById(id);
        songUpdate.setTitle(songDTO.getTitle());
        songUpdate.setYear(songDTO.getYear());
        songUpdate.setArtistSongs(songDTO.getArtistSongs());

        Song newSong = songDAO.updateSong(songUpdate);
        return mapToDTO(newSong);
    }


    @Override
    public void deleteSong(long id) {
        songDAO.deleteSong(id);

    }

    @Override
    public List<Artist> getSongOwner(long id) {
        return artistDAO.getSongOwner(id);
    }

    private Song mapToEntity(SongDTO songDTO) {
        return modelMapper.map(songDTO, Song.class);
    }

    private SongDTO mapToDTO(Song newSong) {
        return modelMapper.map(newSong, SongDTO.class);
    }
}
