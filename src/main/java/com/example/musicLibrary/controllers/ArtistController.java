package com.example.musicLibrary.controllers;

import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.services.impl.ArtistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.createArtist(artist);
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable long id) {
        return artistService.getArtistById(id);
    }

    @GetMapping("/all")
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@RequestBody ArtistDTO artistDTO, @PathVariable(name = "id") long id) {
        ArtistDTO response = artistService.updateArtist(artistDTO, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable(name = "id") long id) {
        artistService.deleteArtist(id);
    }
}
