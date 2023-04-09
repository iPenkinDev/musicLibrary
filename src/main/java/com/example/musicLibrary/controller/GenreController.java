package com.example.musicLibrary.controller;

import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.GenreForm;
import com.example.musicLibrary.services.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/create/")
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO) {
        GenreDTO genreCreateDTO = genreService.createGenre(genreDTO);
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

    @GetMapping("/find/{title}")
    public GenreDTO findGenreByTitle(@PathVariable String title) {
        return genreService.findGenreByTitle(title);
    }

}
