package com.example.musicLibrary.dao;

import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.enumeration.ArtistSortBy;
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

@Repository
public class ArtistDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ArtistDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Artist createArtist(Artist artist) {
        entityManager.persist(artist);
        return artist;
    }

    @Transactional(readOnly = true)
    public Artist getArtistById(long id) {
       return entityManager.find(Artist.class, id);
    }

    @Transactional
    public Page<Artist> getAllArtistsPages(Pageable pageable, ArtistSortBy sortBy, SortDirection sortDir) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Artist> query = cb.createQuery(Artist.class);
        Root<Artist> artist = query.from(Artist.class);
        query.select(artist);

        addSortToCriteria(sortBy, sortDir, cb, query, artist);

        TypedQuery<Artist> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Artist> resultList = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Artist.class)));
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageable, totalCount);
    }

    @Transactional
    public Artist updateArtist(Artist artist) {
        entityManager.merge(artist);
        System.out.println("Artist with name " + artist.getName() + " was updated");
        return artist;
    }

    @Transactional
    public void deleteArtist(long id) {
        entityManager.remove(getArtistById(id));
        System.out.println("Artist with id " + id + " was deleted");
    }

    @Transactional(readOnly = true)
    public Artist findArtistByName(String name) {
        TypedQuery<Artist> query = entityManager.createQuery("SELECT a FROM Artist a WHERE a.name = :name", Artist.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    private static void addSortToCriteria(ArtistSortBy sortBy, SortDirection sortDir, CriteriaBuilder cb, CriteriaQuery<Artist> query, Root<Artist> artist) {
        Order order = null;
        if (sortDir == SortDirection.ASC) {
            switch (sortBy) {
                case ID -> order = cb.asc(artist.get("id"));
                case NAME -> order = cb.asc(artist.get("name"));
                case COUNTRY -> order = cb.asc(artist.get("country"));
                case DATE_OF_BIRTH -> order = cb.asc(artist.get("date_of_birth"));
            }
        } else {
            switch (sortBy) {
                case ID -> order = cb.desc(artist.get("id"));
                case NAME -> order = cb.desc(artist.get("name"));
                case COUNTRY -> order = cb.desc(artist.get("country"));
                case DATE_OF_BIRTH -> order = cb.desc(artist.get("date_of_birth"));
            }

        }
        query.orderBy(order);
    }
}
