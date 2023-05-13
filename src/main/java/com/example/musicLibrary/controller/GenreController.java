package com.example.musicLibrary.controller;

import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.response.GenreResponse;
import com.example.musicLibrary.enumeration.GenreSortBy;
import com.example.musicLibrary.enumeration.SortDirection;
import com.example.musicLibrary.services.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping()
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO) {
        GenreDTO genreCreateDTO = genreService.createGenre(genreDTO);
        return new ResponseEntity<>(genreCreateDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public GenreDTO getGenreById(@PathVariable long id) {
        return genreService.getGenreById(id);
    }

    @GetMapping()
    public GenreResponse getAllAlbumsPages(
            @Valid @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "ID", required = false) GenreSortBy sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) SortDirection sortDir
    ) {
        return genreService.getAllGenresPages(page, pageSize, sortBy, sortDir);
    }

    @PutMapping("/{id}/song/{song_id}")
    public ResponseEntity<GenreDTO> updateGenre(@Valid @RequestBody GenreDTO genreDTO,
                                                long id,
                                                @PathVariable("song_id") long songId) {
        GenreDTO genreUpdateDTO = genreService.updateGenre(genreDTO, id, songId);
        return new ResponseEntity<>(genreUpdateDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") long id) {
        genreService.deleteGenre(id);
    }

    @GetMapping("/songs/{genreId}")
    public List<SongDTO> getAllSongsByGenreId(@PathVariable long genreId) {
        return genreService.getAllSongsByGenreId(genreId);
    }

    @GetMapping("/find/{title}")
    public GenreDTO findGenreByTitle(@PathVariable String title) {
        return genreService.findGenreByTitle(title);
    }
}
