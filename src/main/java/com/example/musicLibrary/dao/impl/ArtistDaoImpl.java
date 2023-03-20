package com.example.musicLibrary.dao.impl;

import com.example.musicLibrary.dao.ArtistDAO;
import com.example.musicLibrary.models.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class ArtistDaoImpl implements ArtistDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ArtistDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Artist createArtist(Artist artist) {
        entityManager.persist(artist);
        return artist;
    }

    @Override
    public Artist getArtistById(long id) {
       return entityManager.find(Artist.class, id);
    }

    @Override
    public List<Artist> getAllArtists() {
        return entityManager.createQuery("SELECT a FROM artists a", Artist.class).getResultList();
    }

    @Override
    public void updateArtist(Artist artist, long id) {
        Artist artistToUpdate = getArtistById(id);
        artistToUpdate.setName(artist.getName());
        artistToUpdate.setGenres(artist.getGenres());
        artistToUpdate.setAlbums(artist.getAlbums());
        artistToUpdate.setSongs(artist.getSongs());
        entityManager.persist(artistToUpdate);
    }

    @Override
    public void deleteArtist(long id) {
        entityManager.remove(getArtistById(id));
        System.out.println("Artist with id " + id + " was deleted");
    }

}
