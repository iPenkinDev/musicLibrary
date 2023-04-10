package com.example.musicLibrary.controller;

import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.dto.forms.SongForm;
import com.example.musicLibrary.services.SongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/{album_id}/genre/{genre_id}")
    public ResponseEntity<SongDTO> createSong(@RequestBody SongDTO songDTO, @PathVariable(name = "album_id") long albumId,
                                              @PathVariable (name = "genre_id") long genreId) {
        SongDTO songCreateDTO = songService.createSong(songDTO, albumId, genreId);
        return new ResponseEntity<>(songCreateDTO, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public SongDTO getSongById(@PathVariable long id) {
        return songService.getSongById(id);
    }

    @GetMapping()
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs();
    }

    @PutMapping("/{id}/album/{album_id}/genre/{genre_id}")
    public ResponseEntity<SongDTO> updateSong(@RequestBody @Valid SongDTO songDTO,
                                              @PathVariable long id,
                                              @PathVariable (name = "album_id") long albumId,
                                              @PathVariable (name = "genre_id") long genreId) {
        SongDTO response = songService.updateSong(songDTO, id, albumId, genreId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable(name = "id") long id) {
        songService.deleteSong(id);
    }

    @GetMapping("/albums/{album_id}")
    public List<SongDTO> getSongsByAlbumId(@PathVariable(name = "album_id") long albumId) {
        return songService.getSongsByAlbumId(albumId);
    }

    @GetMapping("/albums/{album_id}/song/{song_id}")
    public SongDTO getSongByAlbumIdAndSongId(@PathVariable(name = "album_id") long albumId, @PathVariable(name = "song_id") long songId) {
        return songService.getSongByAlbumIdAndSongId(albumId, songId);
    }

    @GetMapping("/artists/{artist_id}")
    public List<SongDTO> getSongsByArtistId(@PathVariable(name = "artist_id") long artistId) {
        return songService.getSongsByArtistId(artistId);
    }

    @GetMapping("/artists/{artist_id}/song/{song_id}")
    public SongDTO getSongByArtistIdAndSongId(@PathVariable(name = "artist_id") long artistId, @PathVariable(name = "song_id") long songId) {
        return songService.getSongByArtistIdAndSongId(artistId, songId);
    }

    @DeleteMapping("/genres/{genre_id}")
    public void deleteSongByGenreId(@PathVariable(name = "genre_id") long genreId) {
        songService.deleteSongByGenreId(genreId);
    }

    @GetMapping("/genres/{genre_id}")
    public List<SongDTO> getAllSongsByGenreId(@PathVariable(name = "genre_id") long genreId) {
        return songService.getAllSongsByGenreId(genreId);
    }

    @GetMapping("/find/{title}")
    public SongDTO findSongByTitle(@PathVariable(name = "title") String title) {
        return songService.findSongByTitle(title);
    }
}
