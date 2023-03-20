package com.example.musicLibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "albums")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

        @Id
        @Column(name = "id")
        private long id;

        @Column(name = "album_title")
        private String title;

        @Column(name = "album_year")
        private int year;

        @ManyToOne
        @JoinColumn(name = "artist_id", referencedColumnName = "id")
        private Artist artistAlbums;

        @ManyToOne
        @JoinColumn(name = "genre_id", referencedColumnName = "id")
        private Genre genre;

}
