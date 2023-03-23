package com.example.musicLibrary.dao.impl;

import com.example.musicLibrary.dao.ArtistDAO;
import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.models.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        TypedQuery<Artist> query = entityManager.createQuery("SELECT a FROM Artist a", Artist.class);
        return query.getResultList();
    }

    @Override
    public Artist updateArtist(Artist artist) {
        entityManager.merge(artist);
        System.out.println("Artist with name " + artist.getName() + " was updated");
        return artist;
    }

    @Override
    public void deleteArtist(long id) {
        entityManager.remove(getArtistById(id));
        System.out.println("Artist with id " + id + " was deleted");
    }

    @Override
    public List<Song> getSongsByArtistId(long id) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.artistSongs.id = :id", Song.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
