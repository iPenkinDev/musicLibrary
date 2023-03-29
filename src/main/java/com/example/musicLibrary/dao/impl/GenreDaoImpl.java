package com.example.musicLibrary.dao.impl;

import com.example.musicLibrary.dao.GenreDAO;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional(readOnly = true)
public class GenreDaoImpl implements GenreDAO {

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
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre g", Genre.class);
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
    public void addGenreToSong(long genreId, long songId) {
        Genre genre = getGenreById(genreId);
        Song song = entityManager.find(Song.class, songId);
        song.getGenres().add(genre);
        genre.getSongs().add(song);
        entityManager.merge(song);
        entityManager.merge(genre);
    }

    @Override
    public void removeGenreFromSong(long genreId, long songId) {
        Genre genre = getGenreById(genreId);
        Song song = entityManager.find(Song.class, songId);
        song.getGenres().remove(genre);
        genre.getSongs().remove(song);
        entityManager.merge(song);
        entityManager.merge(genre);
    }

    @Override
    public List<Genre> getGenresBySongId(long songId) {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM song_genre g WHERE g.song_id = :songId", Genre.class);
        query.setParameter("songId", songId);
        return query.getResultList();
    }
}
