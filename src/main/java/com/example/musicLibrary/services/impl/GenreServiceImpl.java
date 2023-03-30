package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.impl.GenreDaoImpl;
import com.example.musicLibrary.dao.impl.SongDaoImpl;
import com.example.musicLibrary.dto.GenreDTO;
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
    public void addSongToGenre(long genreId, long songId) {
        Genre genre = genreDao.getGenreById(genreId);
        Song song = songDao.getSongById(songId);
        List<Song> songs = genre.getSongs();
        songs.add(song);
        genre.setSongs(songs);
        genreDao.updateGenre(genre);
    }

    @Override
    public void removeSongToGenre(long genreId, long songId) {
        Genre genre = genreDao.getGenreById(genreId);
        Song song = songDao.getSongById(songId);
        List<Song> songs = genre.getSongs();
        songs.remove(song);
        genre.setSongs(songs);
        genreDao.updateGenre(genre);
    }

    @Override
    public List<GenreDTO> getGenresBySongId(long id) {
        return genreDao.getGenresBySongId(id).stream().map(this::mapToDTO).toList();
    }

    private Genre mapToEntity(GenreDTO genreDTO) {
        return modelMapper.map(genreDTO, Genre.class);
    }

    private GenreDTO mapToDTO(Genre newGenre) {
        GenreDTO dto = new GenreDTO();

        dto.setId(newGenre.getId());
        return modelMapper.map(newGenre, GenreDTO.class);
    }
}
