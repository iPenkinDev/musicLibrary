package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import com.example.musicLibrary.enumeration.GenreSortBy;
import com.example.musicLibrary.enumeration.SortDirection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SuppressWarnings("JpaQlInspection")
@Repository
public class GenreDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public GenreDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Genre createGenre(Genre genre) {
        entityManager.persist(genre);
        return genre;
    }

    @Transactional(readOnly = true)
    public Genre getGenreById(long id) {
        return entityManager.find(Genre.class, id);
    }

    @Transactional
    public Page<Genre> getAllGenresPages(Pageable pageable, GenreSortBy sortBy, SortDirection sortDir) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Genre> query = cb.createQuery(Genre.class);
        Root<Genre> genre = query.from(Genre.class);
        query.select(genre);

        addSortToCriteria(sortBy, sortDir, cb, query, genre);

        TypedQuery<Genre> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Genre> resultList = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Genre.class)));
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageable, totalCount);
    }

    @Transactional
    public Genre updateGenre(Genre genre) {
        entityManager.merge(genre);
        System.out.println("Genre with title " + genre.getTitle() + " was updated");
        return genre;
    }

    @Transactional
    public void deleteGenre(long id) {
        entityManager.remove(getGenreById(id));
        System.out.println("Genre with id " + id + " was deleted");

    }

    @Transactional(readOnly = true)
    public List<Song> getAllSongsByGenreId(long genreId) {
        Genre genre = entityManager.find(Genre.class, genreId);
        System.out.println("Get songs: " + genre.getSongs());
        return genre.getSongs();
    }

    @Transactional(readOnly = true)
    public Genre findGenreByTitle(String title) {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre g WHERE g.title = :title", Genre.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    private static void addSortToCriteria(GenreSortBy sortBy, SortDirection sortDir, CriteriaBuilder cb, CriteriaQuery<Genre> query, Root<Genre> genre) {
        Order order = null;
        if (sortDir == SortDirection.ASC) {
            switch (sortBy) {
                case ID -> order = cb.asc(genre.get("id"));
                case TITLE -> order = cb.asc(genre.get("title"));
            }
        } else {
            switch (sortBy) {
                case ID -> order = cb.desc(genre.get("id"));
                case TITLE -> order = cb.desc(genre.get("title"));
            }
        }
        query.orderBy(order);
    }
}
