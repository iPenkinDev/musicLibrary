package com.example.musicLibrary.dao.impl;

import com.example.musicLibrary.dao.GenreDAO;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional(readOnly = true)
public class GenreDaoImpl implements GenreDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public GenreDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Genre createGenre(Genre genre) {
        entityManager.persist(genre);
        return genre;
    }

    @Override
    public Genre getGenreById(long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM genres g", Genre.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Genre updateGenre(Genre genre) {
        entityManager.merge(genre);
        System.out.println("Genre with title " + genre.getTitle() + " was updated");
        return genre;
    }

    @Override
    @Transactional
    public void deleteGenre(long id) {
        entityManager.remove(getGenreById(id));
        System.out.println("Genre with id " + id + " was deleted");

    }

    @Override
    @Transactional
    public void deleteGenreBySongId(long songId) {
        Song song = entityManager.find(Song.class, songId);
        List<Genre> genres = song.getGenres();
        for (Genre genre : genres) {
            genre.getSongs().remove(song);
        }
    }

    @Override
    public List<Genre> getAllGenresBySongId(long songId) {
        Song song = entityManager.find(Song.class, songId);
        return song.getGenres();
    }

    @Override
    public List<Song> getAllSongsByGenreId(long genreId) {
        Genre genre = entityManager.find(Genre.class, genreId);
        System.out.println("Get songs: " + genre.getSongs());
        return genre.getSongs();
    }

    @Override
    public Genre findGenreByTitle(String title) {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM genres g WHERE g.title = :title", Genre.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }
}
