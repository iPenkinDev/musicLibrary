package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.AlbumDAO;
import com.example.musicLibrary.dao.ArtistDAO;
import com.example.musicLibrary.dao.GenreDAO;
import com.example.musicLibrary.dao.SongDAO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.entity.Album;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import com.example.musicLibrary.exception.ApplicationException;
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
    private final ArtistDAO artistDao;
    private final SongMapper songMapper;


    @Autowired
    public SongServiceImpl(AlbumDAO albumDao, SongDAO songDao, GenreDAO genreDao, ArtistDAO artistDao, SongMapper songMapper) {
        this.albumDao = albumDao;
        this.songDao = songDao;
        this.genreDao = genreDao;
        this.artistDao = artistDao;
        this.songMapper = songMapper;
    }

    @Override
    public SongDTO createSong(SongDTO songDTO, long albumId, long genreId) {
        Album album = albumDao.getAlbumById(albumId);
        Genre genre = genreDao.getGenreById(genreId);
        if (album == null || genre == null) {
            throw new ApplicationException("Album or Genre not found");
        }
        List<Genre> genreList = List.of(genre);
        songDTO.setAlbum(album);
        songDTO.setGenres(genreList);
        return songMapper.mapToDTO(songDao.createSong(songMapper.mapToEntity(songDTO)));
    }

    @Override
    public SongDTO getSongById(long id) {
        if (songDao.getSongById(id) == null)
            throw new ApplicationException("Song not found");
        return songMapper.mapToDTO(songDao.getSongById(id));
    }

    @Override
    public List<SongDTO> getAllSongs() {
        List<Song> songList = songDao.getAllSongs();
        if (songList.isEmpty())
            throw new ApplicationException("Song list is empty");
        return songList.stream().map(songMapper::mapToDTO).toList();
    }

    @Override
    public SongDTO updateSong(SongDTO songDTO, long id, long albumId, long genreId) {
        Song songUpdate = songDao.getSongById(id);
        Album album = albumDao.getAlbumById(albumId);
        if (songUpdate == null || album == null) {
            throw new ApplicationException("Song or Album not found");
        }
        List<Genre> genreList = songUpdate.getGenres();
        genreList.add(genreDao.getGenreById(genreId));
        songUpdate.setTitle(songDTO.getTitle());
        songUpdate.setYear(songDTO.getYear());
        songUpdate.setAlbum(album);
        songUpdate.setGenres(genreList);
        Song newSong = songDao.updateSong(songUpdate);
        return songMapper.mapToDTO(newSong);
    }

    @Override
    public void deleteSong(long id) {
        if (songDao.getSongById(id) == null)
            throw new ApplicationException("Song not found");
        songDao.deleteSong(id);
    }

    @Override
    public List<SongDTO> getSongsByAlbumId(long albumId) {
        if (albumDao.getAlbumById(albumId) == null)
            throw new ApplicationException("Song not found");
        List<Song> songList = songDao.getSongsByAlbumId(albumId);
        return songList.stream().map(songMapper::mapToDTO).toList();
    }

    @Override
    public SongDTO getSongByAlbumIdAndSongId(long albumId, long songId) {
        if (albumDao.getAlbumById(albumId) == null || songDao.getSongById(songId) == null)
            throw new ApplicationException("Album or Song not found");
        return songMapper.mapToDTO(songDao.getSongByAlbumIdAndSongId(albumId, songId));
    }

    @Override
    public List<SongDTO> getSongsByArtistId(long artistId) {
        if (artistDao.getArtistById(artistId) == null)
            throw new ApplicationException("Songs for Artist with id " + artistId + " not found");
        List<Song> songList = songDao.getSongsByArtistId(artistId);
        return songList.stream().map(songMapper::mapToDTO).toList();
    }

    @Override
    public SongDTO getSongByArtistIdAndSongId(long artistId, long songId) {
        if (albumDao.getAlbumById(artistId) == null || songDao.getSongById(songId) == null)
            throw new ApplicationException("Artist or Song not found");
        return songMapper.mapToDTO(songDao.getSongByArtistIdAndSongId(artistId, songId));
    }

    @Override
    public void deleteSongByGenreId(long genreId) {
        if (genreDao.getGenreById(genreId) == null)
            throw new ApplicationException("Song not found");
        songDao.deleteSongByGenreId(genreId);
    }

    @Override
    public List<SongDTO> getAllSongsByGenreId(long genreId) {
        if (genreDao.getGenreById(genreId) == null)
            throw new ApplicationException("Song not found");
        List<Song> songList = songDao.getAllSongsByGenreId(genreId);
        return songList.stream().map(songMapper::mapToDTO).toList();
    }

    @Override
    public SongDTO findSongByTitle(String title) {
        if (songDao.findSongByTitle(title) == null)
            throw new ApplicationException("Song not found");
        return songMapper.mapToDTO(songDao.findSongByTitle(title));
    }
}
