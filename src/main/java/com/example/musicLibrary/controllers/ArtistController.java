package com.example.musicLibrary.controllers;

import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.services.ArtistService;
import com.example.musicLibrary.services.impl.ArtistServiceImpl;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private ArtistServiceImpl artistService;

    @Autowired
    public ArtistController(ArtistServiceImpl artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.createArtist(artist);
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable long id) {
        return artistService.getArtistById(id);
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @PutMapping("/{id}")
    public void updateArtist(@RequestBody Artist artist, @PathVariable long id) {
        artistService.updateArtist(artist, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteArtist(@PathVariable long id) {
        artistService.deleteArtist(id);
    }
}
