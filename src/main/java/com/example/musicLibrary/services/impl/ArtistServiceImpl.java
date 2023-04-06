package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.impl.ArtistDaoImpl;
import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.dto.forms.ArtistForm;
import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.services.ArtistService;
import com.example.musicLibrary.util.ArtistMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistDaoImpl artistDao;
    private final ArtistMapper artistMapper;

    @Autowired
    public ArtistServiceImpl(ArtistDaoImpl artistDao, ArtistMapper artistMapper) {
        this.artistDao = artistDao;
        this.artistMapper = artistMapper;
    }

    @Override
    public ArtistDTO createArtist(ArtistDTO artistDTO) {
        return artistMapper.mapToDTO(artistDao.createArtist(artistMapper.mapToEntity(artistDTO)));
    }

    @Override
    public ArtistDTO getArtistById(long id) {
        return artistMapper.mapToDTO(artistDao.getArtistById(id));
    }

    @Override
    public List<ArtistDTO> getAllArtists() {
        return artistDao.getAllArtists().stream().map(artistMapper::mapToDTO).toList();
    }

    @Override
    public ArtistDTO updateArtist(ArtistForm artistForm) {
        Artist artistUpdate = artistDao.getArtistById(artistForm.getId());
        artistUpdate.setName(artistForm.getName());
        artistUpdate.setCountry(artistForm.getCountry());
        artistUpdate.setDateOfBirth(artistUpdate.getDateOfBirth());

        Artist newArtist = artistDao.updateArtist(artistUpdate);
        return artistMapper.mapToDTO(newArtist);
    }

    @Override
    public void deleteArtist(long id) {
        artistDao.deleteArtist(id);
    }

    @Override
    public ArtistDTO findArtistByName(String name) {
        return artistMapper.mapToDTO(artistDao.findArtistByName(name));
    }
}
