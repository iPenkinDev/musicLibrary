package com.example.musicLibrary.util;

import com.example.musicLibrary.dto.SongDTO;
import com.example.musicLibrary.entity.Song;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public SongMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Song mapToEntity(SongDTO songDTO) {
        return modelMapper.map(songDTO, Song.class);
    }

    public SongDTO mapToDTO(Song newSong) {
        return modelMapper.map(newSong, SongDTO.class);
    }
}
