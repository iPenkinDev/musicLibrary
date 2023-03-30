package com.example.musicLibrary.controllers;

import com.example.musicLibrary.dto.GenreDTO;
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

    @PostMapping("/add_song")
    public void addSongToGenre(@RequestParam(name = "genre_id") long genreId, @RequestParam(name = "song_id") long songId) {
        genreService.addSongToGenre(genreId, songId);
    }

    @PostMapping("/remove_song")
    public void removeSongToGenre(@RequestParam(name = "genre_id") long genreId, @RequestParam(name = "song_id") long songId) {
        genreService.removeSongToGenre(genreId, songId);
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<List<GenreDTO>> getGenresBySongId(@PathVariable(name = "id") long id) {
        List<GenreDTO> genreDTOList = genreService.getGenresBySongId(id);
        return ResponseEntity.ok(genreDTOList);
    }

}
