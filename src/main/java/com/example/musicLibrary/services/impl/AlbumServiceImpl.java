package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.impl.AlbumDaoImpl;
import com.example.musicLibrary.dao.impl.ArtistDaoImpl;
import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.dto.forms.AlbumForm;
import com.example.musicLibrary.models.Album;
import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.services.AlbumService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumDaoImpl albumDao;
    private ArtistDaoImpl artistDao;
    private ModelMapper modelMapper;

    @Autowired
    public AlbumServiceImpl(AlbumDaoImpl albumDao, ModelMapper modelMapper, ArtistDaoImpl artistDao) {
        this.albumDao = albumDao;
        this.modelMapper = modelMapper;
        this.artistDao = artistDao;
    }

    @Override
    public AlbumDTO createAlbum(AlbumDTO albumDTO, long artistId) {
        Artist artist = artistDao.getArtistById(artistId);
        albumDTO.setArtistAlbums(artist);

        return mapToDTO(albumDao.createAlbum(mapToEntity(albumDTO)));
    }

    @Override
    public AlbumDTO getAlbumById(long id) {
        return mapToDTO(albumDao.getAlbumById(id));
    }


    @Override
    public AlbumDTO updateAlbum(AlbumForm albumForm) {
        Album albumUpdate = albumDao.getAlbumById(albumForm.getId());
        Artist artist = artistDao.getArtistById(albumForm.getArtistId());
        albumUpdate.setTitle(albumForm.getTitle());
        albumUpdate.setYear(albumForm.getYear());
        albumUpdate.setArtistAlbums(artist);

        Album newAlbum = albumDao.updateAlbum(albumUpdate);
        return mapToDTO(newAlbum);
    }

    @Override
    public void deleteAlbum(long id) {
        albumDao.deleteAlbum(id);
    }

    @Override
    public List<AlbumDTO> getAlbumsByArtistId(long artistId) {
        List<Album> albumList = albumDao.getAlbumsByArtistId(artistId);
        return albumList.stream().map(this::mapToDTO).toList();
    }

    @Override
    public AlbumDTO getAlbumByArtistIdAndAlbumId(long artistId, long albumId) {
        return mapToDTO(albumDao.getAlbumByArtistIdAndAlbumId(artistId, albumId));
    }

    private Album mapToEntity(AlbumDTO albumDTO) {
        return modelMapper.map(albumDTO, Album.class);
    }

    private AlbumDTO mapToDTO(Album newAlbum) {
        AlbumDTO dto = new AlbumDTO();

        dto.setId(newAlbum.getId());
        return modelMapper.map(newAlbum, AlbumDTO.class);
    }
}
