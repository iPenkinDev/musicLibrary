package com.example.musicLibrary.dao.impl;

import com.example.musicLibrary.dao.SongDAO;
import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.models.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDaoImpl implements SongDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SongDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Song createSong(Song song) {
        entityManager.persist(song);
        return song;
    }

    @Override
    public Song getSongById(long id) {
        return entityManager.find(Song.class, id);
    }

    @Override
    public List<Song> getAllSongs() {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s", Song.class);
        return query.getResultList();
    }

    @Override
    public Song updateSong(Song song) {
        entityManager.merge(song);
        System.out.println("Song with title " + song.getTitle() + " was updated");
        return song;
    }

    @Override
    public void deleteSong(long id) {
        entityManager.remove(getSongById(id));
        System.out.println("Song with id " + id + " was deleted");
    }

    @Override
    public List<Artist> getSongOwner (long id) {
        TypedQuery<Artist> query = entityManager.createQuery("SELECT a FROM Artist a WHERE a.id = :id", Artist.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
