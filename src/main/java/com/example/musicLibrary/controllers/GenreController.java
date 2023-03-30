package com.example.musicLibrary.controllers;

import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.GenreForm;
import com.example.musicLibrary.services.impl.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreServiceImpl genreService;

    @Autowired
    public GenreController(GenreServiceImpl genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/create/{songId}")
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO, @PathVariable long songId) {
        GenreDTO genreCreateDTO = genreService.createGenre(genreDTO, songId);
        System.out.println("Genre with title " + genreCreateDTO.getTitle() + " was created");
        return ResponseEntity.ok(genreCreateDTO);
    }

    @GetMapping("/{id}")
    public GenreDTO getGenreById(@PathVariable long id) {
        return genreService.getGenreById(id);
    }

    @GetMapping("/all")
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PutMapping("/update")
    public ResponseEntity<GenreDTO> updateGenre(GenreForm genreForm) {
        GenreDTO genreUpdateDTO = genreService.updateGenre(genreForm);
        return ResponseEntity.ok(genreUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable(name = "id") long id) {
        genreService.deleteGenre(id);
    }

    @GetMapping("/all/{songId}")
    public List<GenreDTO> getAllGenresBySongId(@PathVariable long songId) {
        return genreService.getAllGenresBySongId(songId);
    }

    @GetMapping("/all/songs/{genreId}")
    public List<SongDTO> getAllSongsByGenreId(@PathVariable long genreId) {
        return genreService.getAllSongsByGenreId(genreId);
    }

}
