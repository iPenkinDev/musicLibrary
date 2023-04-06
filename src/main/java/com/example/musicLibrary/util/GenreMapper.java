package com.example.musicLibrary.util;

import com.example.musicLibrary.dto.GenreDTO;
import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.entity.Genre;
import com.example.musicLibrary.entity.Song;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public GenreMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Genre mapToEntity(GenreDTO genreDTO) {
        return modelMapper.map(genreDTO, Genre.class);
    }

    public SongDTO mapToDTO(Song newSong) {
        return modelMapper.map(newSong, SongDTO.class);
    }

    public GenreDTO mapToDTO(Genre newGenre) {
        return modelMapper.map(newGenre, GenreDTO.class);
    }
}
