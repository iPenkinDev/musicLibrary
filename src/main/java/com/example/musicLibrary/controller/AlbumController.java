package com.example.musicLibrary.controller;

import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.dto.forms.AlbumForm;
import com.example.musicLibrary.services.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/create/{artist_id}")
    public ResponseEntity<AlbumDTO> createAlbum(@Valid @RequestBody AlbumDTO albumDTO, @PathVariable(name = "artist_id") long artistId) {
        AlbumDTO albumCreateDTO = albumService.createAlbum(albumDTO, artistId);
        return new ResponseEntity<>(albumCreateDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable long id) {
        AlbumDTO albumDTO = albumService.getAlbumById(id);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        List<AlbumDTO> albumDTOList = albumService.getAllAlbums();
        return new ResponseEntity<>(albumDTOList, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<AlbumDTO> updateAlbum(@Valid @RequestBody AlbumForm albumForm) {
        AlbumDTO response = albumService.updateAlbum(albumForm);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable(name = "id") long id) {
        albumService.deleteAlbum(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/artist/{artist_id}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByArtistId(@PathVariable(name = "artist_id") long artistId) {
        List<AlbumDTO> albumDTOList = albumService.getAlbumsByArtistId(artistId);
        return new ResponseEntity<>(albumDTOList, HttpStatus.OK);
    }

    @GetMapping("/artist/{artist_id}/album/{album_id}")
    public ResponseEntity<AlbumDTO> getAlbumByArtistIdAndAlbumId(@PathVariable(name = "artist_id") long artistId, @PathVariable(name = "album_id") long albumId) {
        AlbumDTO albumDTO = albumService.getAlbumByArtistIdAndAlbumId(artistId, albumId);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @GetMapping("/find/{title}")
    public ResponseEntity<AlbumDTO> findAlbumByTitle(@PathVariable String title) {
        AlbumDTO albumDTO = albumService.findAlbumByTitle(title);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }
}
