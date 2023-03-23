package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.impl.ArtistDaoImpl;
import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.models.Song;
import com.example.musicLibrary.services.ArtistService;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private ArtistDaoImpl artistDao;
    private ModelMapper modelMapper;

    @Autowired
    public ArtistServiceImpl(ArtistDaoImpl artistDao, ModelMapper modelMapper) {
        this.artistDao = artistDao;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Artist createArtist(Artist artist) {
        return artistDao.createArtist(artist);
    }

    @Override
    public Artist getArtistById(long id) {
        return artistDao.getArtistById(id);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistDao.getAllArtists();
    }

    @Override
    @Transactional
    public ArtistDTO updateArtist(ArtistDTO artistDTO, long id) {
        Artist artistUpdate = artistDao.getArtistById(id);
        artistUpdate.setName(artistDTO.getName());
        artistUpdate.setCountry(artistDTO.getCountry());
        artistUpdate.setDateOfBirth(artistDTO.getDateOfBirth());

        Artist newArtist = artistDao.updateArtist(artistUpdate);
        return mapToDTO(newArtist);
    }

    @Override
    @Transactional
    public void deleteArtist(long id) {
        artistDao.deleteArtist(id);
    }

    public List<Song> getSongsByArtistId(long id) {
        return artistDao.getArtistById(id).getSongs();
    }

    private Artist mapToEntity(ArtistDTO artistDTO) {
        return modelMapper.map(artistDTO, Artist.class);
    }

    private ArtistDTO mapToDTO(Artist newArtist) {
        return modelMapper.map(newArtist, ArtistDTO.class);
    }
}
