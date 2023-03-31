package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.impl.ArtistDaoImpl;
import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.dto.forms.ArtistForm;
import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.services.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistDaoImpl artistDao;
    private final ModelMapper modelMapper;

    @Autowired
    public ArtistServiceImpl(ArtistDaoImpl artistDao, ModelMapper modelMapper) {
        this.artistDao = artistDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArtistDTO createArtist(ArtistDTO artistDTO) {
        return mapToDTO(artistDao.createArtist(mapToEntity(artistDTO)));
    }

    @Override
    public ArtistDTO getArtistById(long id) {
        return mapToDTO(artistDao.getArtistById(id));
    }

    @Override
    public List<ArtistDTO> getAllArtists() {
        return artistDao.getAllArtists().stream().map(this::mapToDTO).toList();
    }

    @Override
    public ArtistDTO updateArtist(ArtistForm artistForm) {
        Artist artistUpdate = artistDao.getArtistById(artistForm.getId());
        artistUpdate.setName(artistForm.getName());
        artistUpdate.setCountry(artistForm.getCountry());
        artistUpdate.setDateOfBirth(artistUpdate.getDateOfBirth());

        Artist newArtist = artistDao.updateArtist(artistUpdate);
        return mapToDTO(newArtist);
    }

    @Override
    public void deleteArtist(long id) {
        artistDao.deleteArtist(id);
    }

    @Override
    public ArtistDTO findArtistByName(String name) {
        return mapToDTO(artistDao.findArtistByName(name));
    }

    private Artist mapToEntity(ArtistDTO artistDTO) {
        return modelMapper.map(artistDTO, Artist.class);
    }

    private ArtistDTO mapToDTO(Artist newArtist) {
        return modelMapper.map(newArtist, ArtistDTO.class);
    }
}
