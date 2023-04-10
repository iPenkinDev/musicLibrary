package com.example.musicLibrary.controller;

import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.services.impl.ArtistServiceImpl;
import jakarta.validation.Valid;
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

    @PostMapping()
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artistDTO) {
            ArtistDTO artistCreateDTO = artistService.createArtist(artistDTO);
            return ResponseEntity.ok(artistCreateDTO);
    }

    @GetMapping("/{id}")
    public ArtistDTO getArtistById(@PathVariable long id) {
        return artistService.getArtistById(id);
    }

//    @GetMapping()
//    public List<ArtistDTO> getAllArtists() {
//        return artistService.getAllArtists();
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ArtistDTO> updateArtist(@Valid @RequestBody ArtistDTO artistDTO, @PathVariable(name = "id") long id) {
//        ArtistDTO response = artistService.updateArtist(artistDTO, id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @DeleteMapping("/{id}")
//    public void deleteArtist(@PathVariable(name = "id") long id) {
//        artistService.deleteArtist(id);
//    }

//    @GetMapping("/{name}")
//    public ArtistDTO findArtistByName(@PathVariable String name) {
//        return artistService.findArtistByName(name);
//    }
}
