package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.ArtistDAO;
import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.dto.forms.ArtistForm;
import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.services.ArtistService;
import com.example.musicLibrary.util.ArtistMapper;
import jakarta.persistence.EntityNotFoundException;
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
            throw new EntityNotFoundException("Artist with id " + id + " not found");
        }
        return artistMapper.mapToDTO(artistDao.getArtistById(id));
    }

    @Override
    public List<ArtistDTO> getAllArtists() {
        if (artistDao.getAllArtists().isEmpty())
            throw new EntityNotFoundException("Artists not found");
        return artistDao.getAllArtists().stream().map(artistMapper::mapToDTO).toList();
    }

    @Override
    public ArtistDTO updateArtist(ArtistForm artistForm) {
        if (artistDao.getArtistById(artistForm.getId()) == null) {
            throw new EntityNotFoundException("Artist with id " + artistForm.getId() + " not found");
        }
        Artist artistUpdate = artistDao.getArtistById(artistForm.getId());
        artistUpdate.setName(artistForm.getName());
        artistUpdate.setCountry(artistForm.getCountry());
        artistUpdate.setYearOfBirth(artistForm.getYearOfBirth());

        Artist newArtist = artistDao.updateArtist(artistUpdate);
        return artistMapper.mapToDTO(newArtist);
    }

    @Override
    public void deleteArtist(long id) {
        if (artistDao.getArtistById(id) == null) {
            throw new EntityNotFoundException();
        }
        artistDao.deleteArtist(id);
    }

    @Override
    public ArtistDTO findArtistByName(String name) {
        return artistMapper.mapToDTO(artistDao.findArtistByName(name));
    }
}
