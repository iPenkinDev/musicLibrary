package com.example.musicLibrary.controller;

import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.GenreForm;
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

    @PostMapping("/{song_id}")
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO,
            @PathVariable("song_id") long songId) {
        GenreDTO genreCreateDTO = genreService.createGenre(genreDTO, songId);
        System.out.println("Genre with title " + genreCreateDTO.getTitle() + " was created");
        return new ResponseEntity<>(genreCreateDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public GenreDTO getGenreById(@PathVariable long id) {
        return genreService.getGenreById(id);
    }

    @GetMapping()
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
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

    @GetMapping("/songs/{songId}")
    public List<GenreDTO> getAllGenresBySongId(@PathVariable long songId) {
        return genreService.getAllGenresBySongId(songId);
    }

    @GetMapping("/genres/{genreId}")
    public List<SongDTO> getAllSongsByGenreId(@PathVariable long genreId) {
        return genreService.getAllSongsByGenreId(genreId);
    }

    @GetMapping("/find/{title}")
    public GenreDTO findGenreByTitle(@PathVariable String title) {
        return genreService.findGenreByTitle(title);
    }

}
