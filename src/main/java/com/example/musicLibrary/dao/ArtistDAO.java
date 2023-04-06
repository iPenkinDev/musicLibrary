package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ArtistDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ArtistDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Artist createArtist(Artist artist) {
        entityManager.persist(artist);
        return artist;
    }

    @Transactional(readOnly = true)
    public Artist getArtistById(long id) {
       return entityManager.find(Artist.class, id);
    }

    @Transactional(readOnly = true)
    public List<Artist> getAllArtists() {
        TypedQuery<Artist> query = entityManager.createQuery("SELECT a FROM Artist a", Artist.class);
        return query.getResultList();
    }

    @Transactional
    public Artist updateArtist(Artist artist) {
        entityManager.merge(artist);
        System.out.println("Artist with name " + artist.getName() + " was updated");
        return artist;
    }

    @Transactional
    public void deleteArtist(long id) {
        entityManager.remove(getArtistById(id));
        System.out.println("Artist with id " + id + " was deleted");
    }

    @Transactional(readOnly = true)
    public Artist findArtistByName(String name) {
        TypedQuery<Artist> query = entityManager.createQuery("SELECT a FROM Artist a WHERE a.name = :name", Artist.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
