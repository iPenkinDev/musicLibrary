package com.example.musicLibrary.services.impl;

import com.example.musicLibrary.dao.GenreDAO;
import com.example.musicLibrary.dao.SongDAO;
import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.response.GenreResponse;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import com.example.musicLibrary.enumeration.GenreSortBy;
import com.example.musicLibrary.enumeration.SortDirection;
import com.example.musicLibrary.exception.ApplicationException;
import com.example.musicLibrary.services.GenreService;
import com.example.musicLibrary.util.GenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDAO genreDao;
    private final SongDAO songDao;
    private final GenreMapper genreMapper;

    @Autowired
    public GenreServiceImpl(GenreDAO genreDao, GenreMapper genreMapper, SongDAO songDao) {
        this.genreDao = genreDao;
        this.songDao = songDao;
        this.genreMapper = genreMapper;
    }

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        return genreMapper.mapToDTO(genreDao.createGenre(genreMapper.mapToEntity(genreDTO)));
    }

    @Override
    public GenreDTO getGenreById(long id) {
        if (genreDao.getGenreById(id) == null)
            throw new ApplicationException("Genre not found");
        return genreMapper.mapToDTO(genreDao.getGenreById(id));
    }

    public GenreResponse getAllGenresPages(int page, int pageSize, GenreSortBy sortBy, SortDirection sortDir) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Genre> allGenresPages = genreDao.getAllGenresPages(pageable, sortBy, sortDir);
        List<Genre> content = allGenresPages.getContent();
        List<GenreDTO> genreDTOList = content.stream().map(genreMapper::mapToDTO).toList();
        GenreResponse genreResponse = new GenreResponse();
        genreResponse.setPage(allGenresPages.getNumber());
        genreResponse.setContent(genreDTOList);
        genreResponse.setPageSize(allGenresPages.getSize());
        genreResponse.setTotalPage(allGenresPages.getTotalPages());
        genreResponse.setTotalElement(allGenresPages.getTotalElements());
        genreResponse.setLast(allGenresPages.isLast());
        return genreResponse;
    }

    @Override
    public GenreDTO updateGenre(GenreDTO genreDTO, long id, long songId) {
        Genre genreUpdate = genreDao.getGenreById(id);
        if (genreUpdate == null || songDao.getSongById(songId) == null) {
            throw new ApplicationException("Genre not found");
        }
        List<Song> songs = genreUpdate.getSongs();
        songs.add(songDao.getSongById(songId));
        genreUpdate.setTitle(genreDTO.getTitle());
        genreUpdate.setSongs(songs);

        Genre newGenre = genreDao.updateGenre(genreUpdate);
        return genreMapper.mapToDTO(newGenre);
    }

    @Override
    public void deleteGenre(long id) {
        if (genreDao.getGenreById(id) == null) {
            throw new ApplicationException("Genre not found");
        } else {
            genreDao.deleteGenre(id);
        }
    }

    @Override
    public List<SongDTO> getAllSongsByGenreId(long genreId) {
        if (genreDao.getGenreById(genreId) == null)
            throw new ApplicationException("Genre not found");
        return genreDao.getAllSongsByGenreId(genreId).stream().map(genreMapper::mapToDTO).toList();
    }

    @Override
    public GenreDTO findGenreByTitle(String title) {
        if (genreDao.findGenreByTitle(title) == null)
            throw new ApplicationException("Genre not found");
        return genreMapper.mapToDTO(genreDao.findGenreByTitle(title));
    }
}
