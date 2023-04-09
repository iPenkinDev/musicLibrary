package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.AlbumDAO;
import com.example.musicLibrary.dao.GenreDAO;
import com.example.musicLibrary.dao.SongDAO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.SongForm;
import com.example.musicLibrary.entity.Album;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import com.example.musicLibrary.services.SongService;
import com.example.musicLibrary.util.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final AlbumDAO albumDao;
    private final SongDAO songDao;
    private final GenreDAO genreDao;
    private final SongMapper songMapper;


    @Autowired
    public SongServiceImpl(AlbumDAO albumDao, SongDAO songDao, GenreDAO genreDao, SongMapper songMapper) {
        this.albumDao = albumDao;
        this.songDao = songDao;
        this.genreDao = genreDao;
        this.songMapper = songMapper;
    }

    @Override
    public SongDTO createSong(SongDTO songDTO, long albumId, long genreId) {
        Album album = albumDao.getAlbumById(albumId);
        Genre genre = genreDao.getGenreById(genreId);
        List<Genre> genreList = List.of(genre);
        songDTO.setAlbum(album);
        songDTO.setGenres(genreList);
        return songMapper.mapToDTO(songDao.createSong(songMapper.mapToEntity(songDTO)));
    }

    @Override
    public SongDTO getSongById(long id) {
        return songMapper.mapToDTO(songDao.getSongById(id));
    }

    @Override
    public List<SongDTO> getAllSongs() {
        List<Song> songList = songDao.getAllSongs();
        return songList.stream().map(songMapper::mapToDTO).toList();
    }

    @Override
    public SongDTO updateSong(SongForm songForm) {
        Song songUpdate = songDao.getSongById(songForm.getId());
        Album album = albumDao.getAlbumById(songForm.getAlbumId());
       List<Genre> genreList = songUpdate.getGenres();
       genreList.add(genreDao.getGenreById(songForm.getGenreId()));
        songUpdate.setTitle(songForm.getTitle());
        songUpdate.setYear(songForm.getYear());
        songUpdate.setAlbum(album);
        songUpdate.setGenres(genreList);
        Song newSong = songDao.updateSong(songUpdate);
        return songMapper.mapToDTO(newSong);
    }

    @Override
    public void deleteSong(long id) {
        songDao.deleteSong(id);
    }

    @Override
    public List<SongDTO> getSongsByAlbumId(long albumId) {
        List<Song> songList = songDao.getSongsByAlbumId(albumId);
        return songList.stream().map(songMapper::mapToDTO).toList();
    }

    @Override
    public SongDTO getSongByAlbumIdAndSongId(long albumId, long songId) {
        return songMapper.mapToDTO(songDao.getSongByAlbumIdAndSongId(albumId, songId));
    }

    @Override
    public List<SongDTO> getSongsByArtistId(long artistId) {
        List<Song> songList = songDao.getSongsByArtistId(artistId);
        return songList.stream().map(songMapper::mapToDTO).toList();
    }

    @Override
    public SongDTO getSongByArtistIdAndSongId(long artistId, long songId) {
        return songMapper.mapToDTO(songDao.getSongByArtistIdAndSongId(artistId, songId));
    }

    @Override
    public void deleteSongByGenreId(long genreId) {
        songDao.deleteSongByGenreId(genreId);
    }

    @Override
    public List<SongDTO> getAllSongsByGenreId(long genreId) {
        List<Song> songList = songDao.getAllSongsByGenreId(genreId);
        return songList.stream().map(songMapper::mapToDTO).toList();
    }

    @Override
    public SongDTO findSongByTitle(String title) {
        return songMapper.mapToDTO(songDao.findSongByTitle(title));
    }
}
