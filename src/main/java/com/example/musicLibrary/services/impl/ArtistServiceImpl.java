package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.impl.ArtistDaoImpl;
import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.services.ArtistService;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private ArtistDaoImpl artistDao;

    @Autowired
    public ArtistServiceImpl(ArtistDaoImpl artistDao) {
        this.artistDao = artistDao;
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
    public void updateArtist(Artist artist, long id) {
        artistDao.updateArtist(artist, id);
    }

    @Override
    @Transactional
    public void deleteArtist(long id) {
        artistDao.deleteArtist(id);
    }
}
