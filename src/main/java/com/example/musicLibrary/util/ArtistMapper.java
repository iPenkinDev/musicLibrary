package com.example.musicLibrary.util;

import com.example.musicLibrary.dto.ArtistDTO;
import com.example.musicLibrary.entity.Artist;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ArtistMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Artist mapToEntity(ArtistDTO artistDTO) {
        return modelMapper.map(artistDTO, Artist.class);
    }

    public ArtistDTO mapToDTO(Artist newArtist) {
        return modelMapper.map(newArtist, ArtistDTO.class);
    }
}
