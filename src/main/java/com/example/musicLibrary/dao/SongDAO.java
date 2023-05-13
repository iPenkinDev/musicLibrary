package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import com.example.musicLibrary.enumeration.SongSortBy;
import com.example.musicLibrary.enumeration.SortDirection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    public Page<Song> getAllSongsPages(Pageable pageable, SongSortBy sortBy, SortDirection sortDir) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Song> query = cb.createQuery(Song.class);
        Root<Song> song = query.from(Song.class);
        query.select(song);

        addSortToCriteria(sortBy, sortDir, cb, query, song);

        TypedQuery<Song> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Song> resultList = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Song.class)));
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageable, totalCount);
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

    private static void addSortToCriteria(SongSortBy sortBy, SortDirection sortDir, CriteriaBuilder cb, CriteriaQuery<Song> query, Root<Song> song) {
        Order order = null;
        if (sortDir == SortDirection.ASC) {
            switch (sortBy) {
                case ID -> order = cb.asc(song.get("id"));
                case TITLE -> order = cb.asc(song.get("title"));
                case YEAR -> order = cb.asc(song.get("year"));
            }
        } else {
            switch (sortBy) {
                case ID -> order = cb.desc(song.get("id"));
                case TITLE -> order = cb.desc(song.get("title"));
                case YEAR -> order = cb.desc(song.get("year"));
            }
        }
        query.orderBy(order);
    }
}
