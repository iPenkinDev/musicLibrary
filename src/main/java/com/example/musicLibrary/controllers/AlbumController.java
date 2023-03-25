package com.example.musicLibrary.controllers;

import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.dto.forms.AlbumForm;
import com.example.musicLibrary.models.Album;
import com.example.musicLibrary.models.Artist;
import com.example.musicLibrary.services.AlbumService;
import com.example.musicLibrary.services.impl.AlbumServiceImpl;
import com.example.musicLibrary.services.impl.ArtistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private AlbumServiceImpl albumService;

    @Autowired
    public AlbumController(AlbumServiceImpl albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/create/{artist_id}")
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO, @PathVariable(name = "artist_id") long artistId) {
        AlbumDTO albumCreateDTO = albumService.createAlbum(albumDTO, artistId);
        return new ResponseEntity<>(albumCreateDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public AlbumDTO getAlbumById(@PathVariable long id) {
        return albumService.getAlbumById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<AlbumDTO> updateAlbum(@RequestBody AlbumForm albumForm) {
        AlbumDTO response = albumService.updateAlbum(albumForm);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    public ResponseEntity<AlbumDTO> updateAlbum(@RequestBody AlbumDTO albumDTO, @PathVariable(name = "id") long id) {
//        AlbumDTO response = albumService.updateAlbum(albumDTO, id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @DeleteMapping("/{id}")
//    public void deleteAlbum(@PathVariable(name = "id") long id) {
//        albumService.deleteAlbum(id);
//    }

//    @GetMapping("/{id}/album-owner")
//    public Artist getAlbumOwner(@PathVariable Long id) {
//        return artistService.getArtistById(id);
//    }
}
