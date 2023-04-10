package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.AlbumDAO;
import com.example.musicLibrary.dao.ArtistDAO;
import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.entity.Album;
import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.services.AlbumService;
import com.example.musicLibrary.util.AlbumMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
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
    public AlbumDTO createAlbum(AlbumDTO albumDTO) {
       Artist artist = artistDao.getArtistById(albumDTO.getArtist().getId());
        if (artist == null)
            throw new EntityNotFoundException("Artist with id " + albumDTO.getArtist().getId() + " not found");
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
    public AlbumDTO updateAlbum(AlbumDTO albumDTO, long id) {
        Album albumUpdate = albumDao.getAlbumById(id);
        if (albumUpdate == null) {
            throw new EntityNotFoundException("Album with id " + id + " not found");
        }
        albumUpdate.setTitle(albumDTO.getTitle());
        albumUpdate.setYear(albumDTO.getYear());

        return albumMapper.mapToDTO(albumDao.updateAlbum(albumUpdate));
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
            throw new EntityNotFoundException();
        return albumMapper.mapToDTO(albumDao.getAlbumByArtistIdAndAlbumId(artistId, albumId));
    }

    @Override
    public AlbumDTO findAlbumByTitle(String title) {
        if (albumDao.findAlbumByTitle(title) == null)
            throw new NoResultException();
        return albumMapper.mapToDTO(albumDao.findAlbumByTitle(title));
    }
}
