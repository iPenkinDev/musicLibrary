package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.ArtistDAO;
import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.exception.ApplicationException;
import com.example.musicLibrary.services.ArtistService;
import com.example.musicLibrary.util.ArtistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistDAO artistDao;
    private final ArtistMapper artistMapper;

    @Autowired
    public ArtistServiceImpl(ArtistDAO artistDao, ArtistMapper artistMapper) {
        this.artistDao = artistDao;
        this.artistMapper = artistMapper;
    }

    @Override
    public ArtistDTO createArtist(ArtistDTO artistDTO) {
        return artistMapper.mapToDTO(artistDao.createArtist(artistMapper.mapToEntity(artistDTO)));
    }

    @Override
    public ArtistDTO getArtistById(long id) {
        if (artistDao.getArtistById(id) == null) {
           throw new ApplicationException("Artist with id " + id + " not found");
        }
        return artistMapper.mapToDTO(artistDao.getArtistById(id));
    }

    @Override
    public List<ArtistDTO> getAllArtists() {
        if (artistDao.getAllArtists().isEmpty())
            throw new ApplicationException("Artists not found");
        return artistDao.getAllArtists().stream().map(artistMapper::mapToDTO).toList();
    }

    @Override
    public ArtistDTO updateArtist(ArtistDTO artistDTO, long id) {
        Artist artistUpdate = artistDao.getArtistById(id);
        if (artistUpdate == null) {
            throw new ApplicationException("Artist with id " + id + " not found");
        }
        artistUpdate.setName(artistDTO.getName());
        artistUpdate.setCountry(artistDTO.getCountry());
        artistUpdate.setYearOfBirth(artistDTO.getYearOfBirth());

        return artistMapper.mapToDTO(artistDao.updateArtist(artistUpdate));
    }

    @Override
    public void deleteArtist(long id) {
        if (artistDao.getArtistById(id) == null) {
            throw new ApplicationException("Artist with id " + id + " not found");
        }
        artistDao.deleteArtist(id);
    }

    @Override
    public ArtistDTO findArtistByName(String name) {
        if (artistDao.findArtistByName(name) == null) {
            throw new ApplicationException();
        }
        return artistMapper.mapToDTO(artistDao.findArtistByName(name));
    }
}
