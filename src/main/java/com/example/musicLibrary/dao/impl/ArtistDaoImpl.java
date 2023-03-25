package com.example.musicLibrary.dao.impl;

import com.example.musicLibrary.dao.ArtistDAO;
import com.example.musicLibrary.entity.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ArtistDaoImpl implements ArtistDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ArtistDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
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
        TypedQuery<Artist> query = entityManager.createQuery("SELECT a FROM Artist a", Artist.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Artist updateArtist(Artist artist) {
        entityManager.merge(artist);
        System.out.println("Artist with name " + artist.getName() + " was updated");
        return artist;
    }

    @Override
    @Transactional
    public void deleteArtist(long id) {
        entityManager.remove(getArtistById(id));
        System.out.println("Artist with id " + id + " was deleted");
    }

}
