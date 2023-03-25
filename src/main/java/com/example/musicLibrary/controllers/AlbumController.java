package com.example.musicLibrary.controllers;

import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.dto.forms.AlbumForm;
import com.example.musicLibrary.entity.Artist;
import com.example.musicLibrary.services.impl.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumServiceImpl albumService;

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

    @GetMapping("/all")
    public List<AlbumDTO> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @PutMapping("/update")
    public ResponseEntity<AlbumDTO> updateAlbum(@RequestBody AlbumForm albumForm) {
        AlbumDTO response = albumService.updateAlbum(albumForm);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable(name = "id") long id) {
        albumService.deleteAlbum(id);
    }

    @GetMapping("/artist/{artist_id}")
    public List<AlbumDTO> getAlbumsByArtistId(@PathVariable(name = "artist_id") long artistId) {
        return albumService.getAlbumsByArtistId(artistId);
    }

    @GetMapping("/artist/{artist_id}/album/{album_id}")
    public AlbumDTO getAlbumByArtistIdAndAlbumId(@PathVariable(name = "artist_id") long artistId, @PathVariable(name = "album_id") long albumId) {
        return albumService.getAlbumByArtistIdAndAlbumId(artistId, albumId);
    }
}
