package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.impl.GenreDaoImpl;
import com.example.musicLibrary.dao.impl.SongDaoImpl;
import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.GenreForm;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import com.example.musicLibrary.services.GenreService;
import com.example.musicLibrary.util.GenreMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDaoImpl genreDao;
    private final SongDaoImpl songDao;
    private final GenreMapper genreMapper;

    @Autowired
    public GenreServiceImpl(GenreDaoImpl genreDao, GenreMapper genreMapper, SongDaoImpl songDao) {
        this.genreDao = genreDao;
        this.songDao = songDao;
        this.genreMapper = genreMapper;
    }

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO, long songId) {
        List<Song> songList = new ArrayList<>();
        songList.add(songDao.getSongById(songId));
        genreDTO.setSongs(songList);
        return genreMapper.mapToDTO(genreDao.createGenre(genreMapper.mapToEntity(genreDTO)));
    }

    @Override
    public GenreDTO getGenreById(long id) {
       return genreMapper.mapToDTO(genreDao.getGenreById(id));
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreDao.getAllGenres().stream().map(genreMapper::mapToDTO).toList();
    }

    @Override
    public GenreDTO updateGenre(GenreForm genreForm) {
        Genre genreUpdate = genreDao.getGenreById(genreForm.getId());
        List<Song> songs = genreUpdate.getSongs();
        songs.add(songDao.getSongById(genreForm.getSongId()));
        genreUpdate.setTitle(genreForm.getTitle());
        genreUpdate.setSongs(songs);

        Genre newGenre = genreDao.updateGenre(genreUpdate);
        return genreMapper.mapToDTO(newGenre);
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
        return genreDao.getAllGenresBySongId(songId).stream().map(genreMapper::mapToDTO).toList();
    }

    @Override
    public List<SongDTO> getAllSongsByGenreId(long genreId) {
        return genreDao.getAllSongsByGenreId(genreId).stream().map(genreMapper::mapToDTO).toList();
    }

    @Override
    public GenreDTO findGenreByTitle(String title) {
        return genreMapper.mapToDTO(genreDao.findGenreByTitle(title));
    }


}
