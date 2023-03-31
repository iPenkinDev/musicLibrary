package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.impl.AlbumDaoImpl;
import com.example.musicLibrary.dao.impl.GenreDaoImpl;
import com.example.musicLibrary.dao.impl.SongDaoImpl;
import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.SongForm;
import com.example.musicLibrary.entity.Album;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import com.example.musicLibrary.services.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final AlbumDaoImpl albumDao;
    private final SongDaoImpl songDao;
    private final GenreDaoImpl genreDao;
    private final ModelMapper modelMapper;

    @Autowired
    public SongServiceImpl(AlbumDaoImpl albumDao, SongDaoImpl songDao, GenreDaoImpl genreDao, ModelMapper modelMapper) {
        this.albumDao = albumDao;
        this.songDao = songDao;
        this.genreDao = genreDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public SongDTO createSong(SongDTO songDTO, long albumId) {
        Album album = albumDao.getAlbumById(albumId);
        songDTO.setAlbum(album);
        return mapToDTO(songDao.createSong(mapToEntity(songDTO)));
    }

    @Override
    public SongDTO getSongById(long id) {
        return mapToDTO(songDao.getSongById(id));
    }

    @Override
    public List<SongDTO> getAllSongs() {
        List<Song> songList = songDao.getAllSongs();
        return songList.stream().map(this::mapToDTO).toList();
    }

    @Override
    public SongDTO updateSong(SongForm songForm) {
        Song songUpdate = songDao.getSongById(songForm.getId());
        Album album = albumDao.getAlbumById(songForm.getAlbumId());
        songUpdate.setTitle(songForm.getTitle());
        songUpdate.setYear(songForm.getYear());
        songUpdate.setAlbum(album);

        Song newSong = songDao.updateSong(songUpdate);
        return mapToDTO(newSong);
    }

    @Override
    public void deleteSong(long id) {
        songDao.deleteSong(id);
    }

    @Override
    public List<SongDTO> getSongsByAlbumId(long albumId) {
        List<Song> songList = songDao.getSongsByAlbumId(albumId);
        return songList.stream().map(this::mapToDTO).toList();
    }

    @Override
    public SongDTO getSongByAlbumIdAndSongId(long albumId, long songId) {
        return mapToDTO(songDao.getSongByAlbumIdAndSongId(albumId, songId));
    }

    @Override
    public List<SongDTO> getSongsByArtistId(long artistId) {
        List<Song> songList = songDao.getSongsByArtistId(artistId);
        return songList.stream().map(this::mapToDTO).toList();
    }

    @Override
    public SongDTO getSongByArtistIdAndSongId(long artistId, long songId) {
        return mapToDTO(songDao.getSongByArtistIdAndSongId(artistId, songId));
    }

    @Override
    public void deleteSongByGenreId(long genreId) {
        songDao.deleteSongByGenreId(genreId);
    }

    @Override
    public List<SongDTO> getAllSongsByGenreId(long genreId) {
        List<Song> songList = songDao.getAllSongsByGenreId(genreId);
        return songList.stream().map(this::mapToDTO).toList();
    }

    @Override
    public SongDTO findSongByTitle(String title) {
        return mapToDTO(songDao.findSongByTitle(title));
    }

    private Song mapToEntity(SongDTO songDTO) {
        return modelMapper.map(songDTO, Song.class);
    }

    private GenreDTO mapToDTO(Genre newGenre) {
        return modelMapper.map(newGenre, GenreDTO.class);
    }

    private SongDTO mapToDTO(Song newSong) {
        return modelMapper.map(newSong, SongDTO.class);
    }
}
