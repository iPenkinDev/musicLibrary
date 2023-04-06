package com.example.musicLibrary.util;

import com.example.musicLibrary.dto.AlbumDTO;
import com.example.musicLibrary.entity.Album;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public AlbumMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Album mapToEntity(AlbumDTO albumDTO) {
        return modelMapper.map(albumDTO, Album.class);
    }

    public AlbumDTO mapToDTO(Album newAlbum) {
        AlbumDTO dto = new AlbumDTO();

        dto.setId(newAlbum.getId());
        return modelMapper.map(newAlbum, AlbumDTO.class);
    }
}
