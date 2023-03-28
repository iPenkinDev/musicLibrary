package com.example.musicLibrary.dao.impl;

import com.example.musicLibrary.dao.SongDAO;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SongDaoImpl implements SongDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
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
    @Transactional
    public Song updateSong(Song song) {
        entityManager.merge(song);
        System.out.println("Song with title " + song.getTitle() + " was updated");
        return song;
    }

    @Override
    @Transactional
    public void deleteSong(long id) {
        entityManager.remove(getSongById(id));
        System.out.println("Song with id " + id + " was deleted");
    }

    @Override
    public List<Song> getSongsByAlbumId(long albumId) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.album.id = :albumId", Song.class);
        query.setParameter("albumId", albumId);
        return query.getResultList();
    }

    @Override
    public Song getSongByAlbumIdAndSongId(long albumId, long songId) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.album.id = :albumId AND s.id = :songId", Song.class);
        query.setParameter("albumId", albumId);
        query.setParameter("songId", songId);
        return query.getSingleResult();
    }

    @Override
    public List<Song> getSongsByArtistId(long artistId) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.album.artist.id = :artistId", Song.class);
        query.setParameter("artistId", artistId);
        return query.getResultList();
    }

    @Override
    public Song getSongByArtistIdAndSongId(long artistId, long songId) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.album.artist.id = :artistId AND s.id = :songId", Song.class);
        query.setParameter("artistId", artistId);
        query.setParameter("songId", songId);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> getGenresBySongId(long songId) {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre g JOIN g.songs s WHERE s.id = :songId", Genre.class);
        query.setParameter("songId", songId);
        return query.getResultList();
    }
}
