package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.impl.GenreDaoImpl;
import com.example.musicLibrary.dao.impl.SongDaoImpl;
import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.GenreForm;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import com.example.musicLibrary.services.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDaoImpl genreDao;
    private final ModelMapper modelMapper;
    private final SongDaoImpl songDao;

    @Autowired
    public GenreServiceImpl(GenreDaoImpl genreDao, ModelMapper modelMapper, SongDaoImpl songDao) {
        this.genreDao = genreDao;
        this.modelMapper = modelMapper;
        this.songDao = songDao;
    }

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO, long songId) {
        List<Song> songList = new ArrayList<>();
        songList.add(songDao.getSongById(songId));
        genreDTO.setSongs(songList);
        return mapToDTO(genreDao.createGenre(mapToEntity(genreDTO)));
    }

    @Override
    public GenreDTO getGenreById(long id) {
       return mapToDTO(genreDao.getGenreById(id));
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreDao.getAllGenres().stream().map(this::mapToDTO).toList();
    }

    @Override
    public GenreDTO updateGenre(GenreForm genreForm) {
        Genre genreUpdate = genreDao.getGenreById(genreForm.getId());
        List<Song> songs = genreUpdate.getSongs();
        songs.add(songDao.getSongById(genreForm.getSongId()));
        genreUpdate.setTitle(genreForm.getTitle());
        genreUpdate.setSongs(songs);

        Genre newGenre = genreDao.updateGenre(genreUpdate);
        return mapToDTO(newGenre);
    }

    @Override
    public void deleteGenre(long id) {
        genreDao.deleteGenre(id);
    }

    @Override
    public void deleteGenreBySongId(long id) {
        genreDao.deleteGenreBySongId(id);
    }

    @Override
    public List<GenreDTO> getAllGenresBySongId(long songId) {
        return genreDao.getAllGenresBySongId(songId).stream().map(this::mapToDTO).toList();
    }

    @Override
    public List<SongDTO> getAllSongsByGenreId(long genreId) {
        return genreDao.getAllSongsByGenreId(genreId).stream().map(this::mapToDTO).toList();
    }

    private Genre mapToEntity(GenreDTO genreDTO) {
        return modelMapper.map(genreDTO, Genre.class);
    }

    private SongDTO mapToDTO(Song newSong) {
        return modelMapper.map(newSong, SongDTO.class);
    }

    private GenreDTO mapToDTO(Genre newGenre) {
        return modelMapper.map(newGenre, GenreDTO.class);
    }
}
