package com.example.musicLibrary.controllers;

import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.SongForm;
import com.example.musicLibrary.services.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongServiceImpl songService;

    @Autowired
    public SongController(SongServiceImpl songService) {
        this.songService = songService;
    }

    @PostMapping("/create/{album_id}")
    public ResponseEntity<SongDTO> createSong(@RequestBody SongDTO songDTO, @PathVariable(name = "album_id") long albumId) {
        SongDTO songCreateDTO = songService.createSong(songDTO, albumId);
        return new ResponseEntity<>(songCreateDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public SongDTO getSongById(@PathVariable long id) {
        return songService.getSongById(id);
    }

    @GetMapping("/all")
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs();
    }

    @PutMapping("/update")
    public ResponseEntity<SongDTO> updateSong(@RequestBody SongForm songForm) {
        SongDTO response = songService.updateSong(songForm);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable(name = "id") long id) {
        songService.deleteSong(id);
    }

    @GetMapping("/album/{album_id}")
    public List<SongDTO> getSongsByAlbumId(@PathVariable(name = "album_id") long albumId) {
        return songService.getSongsByAlbumId(albumId);
    }

    @GetMapping("/album/{album_id}/song/{song_id}")
    public SongDTO getSongByAlbumIdAndSongId(@PathVariable(name = "album_id") long albumId, @PathVariable(name = "song_id") long songId) {
        return songService.getSongByAlbumIdAndSongId(albumId, songId);
    }

    @GetMapping("/artist/{artist_id}")
    public List<SongDTO> getSongsByArtistId(@PathVariable(name = "artist_id") long artistId) {
        return songService.getSongsByArtistId(artistId);
    }

    @GetMapping("/artist/{artist_id}/song/{song_id}")
    public SongDTO getSongByArtistIdAndSongId(@PathVariable(name = "artist_id") long artistId, @PathVariable(name = "song_id") long songId) {
        return songService.getSongByArtistIdAndSongId(artistId, songId);
    }

    @DeleteMapping("/delete/{genre_id}")
    public void deleteSongByGenreId(@PathVariable(name = "genre_id") long genreId) {
        songService.deleteSongByGenreId(genreId);
    }

    @GetMapping("/genre/{genre_id}")
    public List<SongDTO> getAllSongsByGenreId(@PathVariable(name = "genre_id") long genreId) {
        return songService.getAllSongsByGenreId(genreId);
    }

    @GetMapping("/find/{title}")
    public SongDTO findSongByTitle(@PathVariable(name = "title") String title) {
        return songService.findSongByTitle(title);
    }
}
