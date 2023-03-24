package com.example.musicLibrary.dao.impl;

import com.example.musicLibrary.dao.AlbumDAO;
import com.example.musicLibrary.models.Album;
import com.example.musicLibrary.models.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumDaoImpl implements AlbumDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Album createAlbum(Album album) {
        entityManager.persist(album);
        return album;
    }

    @Override
    public Album getAlbumById(long id) {
        return entityManager.find(Album.class, id);
    }

    @Override
    public List<Album> getAllAlbums() {
        TypedQuery<Album> query = entityManager.createQuery("SELECT a FROM Album a", Album.class);
        return query.getResultList();
    }

    @Override
    public Album updateAlbum(Album album) {
        entityManager.merge(album);
        System.out.println("Album with title " + album.getTitle() + " was updated");
        return album;
    }

    @Override
    public void deleteAlbum(long id) {
        entityManager.remove(getAlbumById(id));
        System.out.println("Album with id " + id + " was deleted");

    }

    @Override
    public List<Artist> getArtistsAlbums(long id) {
        TypedQuery<Artist> query = entityManager.createQuery("SELECT a FROM Artist a WHERE a.albums = :id", Artist.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
