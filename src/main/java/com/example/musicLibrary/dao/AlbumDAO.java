package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Album;
import com.example.musicLibrary.enumeration.AlbumSortBy;
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
public class AlbumDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Album createAlbum(Album album) {
        entityManager.merge(album);
        return album;
    }

    @Transactional(readOnly = true)
    public Album getAlbumById(long id) {
        return entityManager.find(Album.class, id);
    }

    @Transactional
    public Page<Album> getAllAlbumsPages(Pageable pageable, AlbumSortBy sortBy, SortDirection sortDir) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Album> query = cb.createQuery(Album.class);
        Root<Album> album = query.from(Album.class);
        query.select(album);

        addSortToCriteria(sortBy, sortDir, cb, query, album);

        TypedQuery<Album> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Album> resultList = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Album.class)));
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageable, totalCount);
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

    private static void addSortToCriteria(AlbumSortBy sortBy, SortDirection sortDir, CriteriaBuilder cb, CriteriaQuery<Album> query, Root<Album> album) {
        Order order = null;
        if (sortDir == SortDirection.ASC) {
            switch (sortBy) {
                case ID -> order = cb.asc(album.get("id"));
                case TITLE -> order = cb.asc(album.get("title"));
                case YEAR -> order = cb.asc(album.get("year"));
            }
        } else {
            switch (sortBy) {
                case ID -> order = cb.desc(album.get("id"));
                case TITLE -> order = cb.desc(album.get("title"));
                case YEAR -> order = cb.desc(album.get("year"));
            }
        }
        query.orderBy(order);
    }
}
