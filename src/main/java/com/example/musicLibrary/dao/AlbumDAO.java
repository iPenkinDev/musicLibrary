package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Album;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class AlbumDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Album createAlbum(Album album) {
        entityManager.persist(album);
        return album;
    }

    @Transactional(readOnly = true)
    public Album getAlbumById(long id) {
        return entityManager.find(Album.class, id);
    }

    @Transactional(readOnly = true)
    public List<Album> getAllAlbums() {
        TypedQuery<Album> query = entityManager.createQuery("SELECT a FROM Album a", Album.class);
        return query.getResultList();
    }

    @Transactional
    public Album updateAlbum(Album album) {
        entityManager.merge(album);
        System.out.println("Album with title " + album.getTitle() + " was updated");
        return album;
    }

    @Transactional
    public void deleteAlbum(long id) {
        entityManager.remove(getAlbumById(id));
        System.out.println("Album with id " + id + " was deleted");

    }

    @Transactional(readOnly = true)
    public List<Album> getAlbumsByArtistId(long artistId) {
        TypedQuery<Album> query = entityManager.createQuery("SELECT a FROM Album a WHERE a.artist.id = :artistId", Album.class);
        query.setParameter("artistId", artistId);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Album getAlbumByArtistIdAndAlbumId(long artistId, long albumId) {
        TypedQuery<Album> query = entityManager
                .createQuery("SELECT a FROM Album a WHERE a.artist.id = :artistId AND a.id = :albumId", Album.class);
        query.setParameter("artistId", artistId);
        query.setParameter("albumId", albumId);
        return query.getSingleResult();
    }

    @Transactional(readOnly = true)
    public Album findAlbumByTitle(String title) {
        TypedQuery<Album> query = entityManager.createQuery("SELECT a FROM Album a WHERE a.title = :title", Album.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }
}
