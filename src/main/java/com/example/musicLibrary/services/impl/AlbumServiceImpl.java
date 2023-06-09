package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.AlbumDAO;
import com.example.musicLibrary.dao.ArtistDAO;
import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.dto.response.AlbumResponse;
import com.example.musicLibrary.entity.Album;
import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.enumeration.AlbumSortBy;
import com.example.musicLibrary.enumeration.SortDirection;
import com.example.musicLibrary.exception.ApplicationException;
import com.example.musicLibrary.services.AlbumService;
import com.example.musicLibrary.util.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumDAO albumDao;
    private final ArtistDAO artistDao;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumServiceImpl(AlbumDAO albumDao, AlbumMapper albumMapper, ArtistDAO artistDao) {
        this.albumDao = albumDao;
        this.artistDao = artistDao;
        this.albumMapper = albumMapper;
    }

    @Override
    public AlbumDTO createAlbum(AlbumDTO albumDTO, long artistId) {
        Artist artist = artistDao.getArtistById(artistId);
        if (artist == null)
            throw new ApplicationException("Artist with id " + artistId + " not found");
        albumDTO.setArtist(artist);
        return albumMapper.mapToDTO(albumDao.createAlbum(albumMapper.mapToEntity(albumDTO)));
    }

    @Override
    public AlbumDTO getAlbumById(long id) {
        if (albumDao.getAlbumById(id) == null)
            throw new ApplicationException("Album with id " + id + " not found");
        return albumMapper.mapToDTO(albumDao.getAlbumById(id));
    }

    @Override
    public AlbumResponse getAllAlbumsPages(int page, int pageSize, AlbumSortBy sortBy, SortDirection sortDir) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Album> allAlbumsPages = albumDao.getAllAlbumsPages(pageable, sortBy, sortDir);
        List<Album> content = allAlbumsPages.getContent();
        List<AlbumDTO> albumDTOList = content.stream().map(albumMapper::mapToDTO).toList();
        AlbumResponse albumResponse = new AlbumResponse();
        albumResponse.setPage(allAlbumsPages.getNumber());
        albumResponse.setContent(albumDTOList);
        albumResponse.setPageSize(allAlbumsPages.getSize());
        albumResponse.setTotalPage(allAlbumsPages.getTotalPages());
        albumResponse.setTotalElement(allAlbumsPages.getTotalElements());
        albumResponse.setLast(allAlbumsPages.isLast());
        return albumResponse;
    }

    @Override
    public AlbumDTO updateAlbum(AlbumDTO albumDTO, long id) {
        Album albumUpdate = albumDao.getAlbumById(id);
        if (albumUpdate == null) {
            throw new ApplicationException();
        }
        albumUpdate.setTitle(albumDTO.getTitle());
        albumUpdate.setYear(albumDTO.getYear());

        return albumMapper.mapToDTO(albumDao.updateAlbum(albumUpdate));
    }

    @Override
    public void deleteAlbum(long id) {
        if (albumDao.getAlbumById(id) == null)
            throw new ApplicationException("Album with id " + id + " not found");
        albumDao.deleteAlbum(id);
    }

    @Override
    public List<AlbumDTO> getAlbumsByArtistId(long artistId) {
        List<Album> albumList = albumDao.getAlbumsByArtistId(artistId);
        if (albumList.isEmpty())
            throw new ApplicationException("Albums not found");
        return albumList.stream().map(albumMapper::mapToDTO).toList();
    }

    @Override
    public AlbumDTO getAlbumByArtistIdAndAlbumId(long artistId, long albumId) {
        if (albumDao.getAlbumByArtistIdAndAlbumId(artistId, albumId) == null)
            throw new ApplicationException("Album with id " + albumId + " not found");
        return albumMapper.mapToDTO(albumDao.getAlbumByArtistIdAndAlbumId(artistId, albumId));
    }

    @Override
    public AlbumDTO findAlbumByTitle(String title) {
        if (albumDao.findAlbumByTitle(title) == null)
            throw new ApplicationException("Album with title " + title + " not found");
        return albumMapper.mapToDTO(albumDao.findAlbumByTitle(title));
    }
}
