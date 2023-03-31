package com.example.musicLibrary.controllers;

import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.dto.forms.AlbumForm;
import com.example.musicLibrary.dto.forms.ArtistForm;
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

    private final ArtistServiceImpl artistService;

    @Autowired
    public ArtistController(ArtistServiceImpl artistService) {
        this.artistService = artistService;
    }

    @PostMapping("/create")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artistDTO) {
        ArtistDTO artistCreateDTO = artistService.createArtist(artistDTO);
        return new ResponseEntity<>(artistCreateDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ArtistDTO getArtistById(@PathVariable long id) {
        return artistService.getArtistById(id);
    }

    @GetMapping("/all")
    public List<ArtistDTO> getAllArtists() {
        return artistService.getAllArtists();
    }

    @PutMapping("/update")
    public ResponseEntity<ArtistDTO> updateArtist(@RequestBody ArtistForm artistForm) {
        ArtistDTO response = artistService.updateArtist(artistForm);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable(name = "id") long id) {
        artistService.deleteArtist(id);
    }

    @GetMapping("/find/{name}")
    public ArtistDTO findArtistByName(@PathVariable String name) {
        return artistService.findArtistByName(name);
    }
}
