package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class SongDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Song createSong(Song song) {
        entityManager.persist(song);
        return song;
    }

    @Transactional(readOnly = true)
    public Song getSongById(long id) {
        return entityManager.find(Song.class, id);
    }

    @Transactional(readOnly = true)
    public List<Song> getAllSongs() {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s", Song.class);
        return query.getResultList();
    }

    @Transactional
    public Song updateSong(Song song) {
        entityManager.merge(song);
        System.out.println("Song with title " + song.getTitle() + " was updated");
        return song;
    }

    @Transactional
    public void deleteSong(long id) {
        entityManager.remove(getSongById(id));
        System.out.println("Song with id " + id + " was deleted");
    }

    @Transactional(readOnly = true)
    public List<Song> getSongsByAlbumId(long albumId) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.album.id = :albumId", Song.class);
        query.setParameter("albumId", albumId);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Song getSongByAlbumIdAndSongId(long albumId, long songId) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.album.id = :albumId AND s.id = :songId", Song.class);
        query.setParameter("albumId", albumId);
        query.setParameter("songId", songId);
        return query.getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<Song> getSongsByArtistId(long artistId) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.album.artist.id = :artistId", Song.class);
        query.setParameter("artistId", artistId);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Song getSongByArtistIdAndSongId(long artistId, long songId) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.album.artist.id = :artistId AND s.id = :songId", Song.class);
        query.setParameter("artistId", artistId);
        query.setParameter("songId", songId);
        return query.getSingleResult();
    }

    @Transactional
    public void deleteSongByGenreId(long genreId) {
        Genre genre = entityManager.find(Genre.class, genreId);
        List<Song> songs = genre.getSongs();
        for (Song song : songs) {
            entityManager.remove(song);
        }
    }

    @Transactional(readOnly = true)
    public List<Song> getAllSongsByGenreId(long genreId) {
        Genre genre = entityManager.find(Genre.class, genreId);
        return genre.getSongs();
    }

    @Transactional(readOnly = true)
    public Song findSongByTitle(String title) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.title = :title", Song.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }
}
