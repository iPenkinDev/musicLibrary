package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.AlbumDAO;
import com.example.musicLibrary.dao.ArtistDAO;
import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.dto.forms.AlbumForm;
import com.example.musicLibrary.entity.Album;
import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.services.AlbumService;
import com.example.musicLibrary.util.AlbumMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumDAO albumDao;
    private final ArtistDAO artistDao;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumServiceImpl(AlbumDAO albumDao, AlbumMapper albumMapper, ArtistDAO artistDao) {
        this.albumDao = albumDao;
        this.artistDao = artistDao;
        this.albumMapper = albumMapper;
    }

    @Override
    public AlbumDTO createAlbum(AlbumDTO albumDTO, long artistId) {
        Artist artist = artistDao.getArtistById(artistId);
        if (artist == null) {
            throw new EntityNotFoundException("Artist with id " + artistId + " not found");
        }
        albumDTO.setArtist(artist);
        return albumMapper.mapToDTO(albumDao.createAlbum(albumMapper.mapToEntity(albumDTO)));
    }

    @Override
    public AlbumDTO getAlbumById(long id) {
        if (albumDao.getAlbumById(id) == null)
            throw new EntityNotFoundException("Album with id " + id + " not found");
        return albumMapper.mapToDTO(albumDao.getAlbumById(id));
    }

    @Override
    public List<AlbumDTO> getAllAlbums() {
        List<Album> albumList = albumDao.getAllAlbums();
        if (albumList.isEmpty())
            throw new EntityNotFoundException("Albums not found");
        return albumList.stream().map(albumMapper::mapToDTO).toList();
    }


    @Override
    public AlbumDTO updateAlbum(AlbumForm albumForm) {
        Album albumUpdate = albumDao.getAlbumById(albumForm.getId());
        if (albumUpdate == null) {
            throw new EntityNotFoundException("Album with id " + albumForm.getId() + " not found");
        }
        Artist artist = artistDao.getArtistById(albumForm.getArtistId());
        if (artist == null) {
            throw new EntityNotFoundException("Artist with id " + albumForm.getArtistId() + " not found");
        }
        albumUpdate.setTitle(albumForm.getTitle());
        albumUpdate.setYear(albumForm.getYear());
        albumUpdate.setArtist(artist);

        Album newAlbum = albumDao.updateAlbum(albumUpdate);
        return albumMapper.mapToDTO(newAlbum);
    }

    @Override
    public void deleteAlbum(long id) {
        if (albumDao.getAlbumById(id) == null)
            throw new EntityNotFoundException("Album with id " + id + " not found");
        albumDao.deleteAlbum(id);
    }

    @Override
    public List<AlbumDTO> getAlbumsByArtistId(long artistId) {
        List<Album> albumList = albumDao.getAlbumsByArtistId(artistId);
        if (albumList.isEmpty())
            throw new EntityNotFoundException("Albums not found");
        return albumList.stream().map(albumMapper::mapToDTO).toList();
    }

    @Override
    public AlbumDTO getAlbumByArtistIdAndAlbumId(long artistId, long albumId) {
        if (albumDao.getAlbumByArtistIdAndAlbumId(artistId, albumId) == null)
            throw new EntityNotFoundException("Album with id " + albumId + " not found");
        return albumMapper.mapToDTO(albumDao.getAlbumByArtistIdAndAlbumId(artistId, albumId));
    }

    @Override
    public AlbumDTO findAlbumByTitle(String title) {
        if (albumDao.findAlbumByTitle(title) == null)
            throw new EntityNotFoundException("Album with title " + title + " not found");
        return albumMapper.mapToDTO(albumDao.findAlbumByTitle(title));
    }
}
