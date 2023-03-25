package com.example.musicLibrary.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "albums")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "album_title")
        private String title;

        @Column(name = "album_year")
        private int year;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "artist_id", referencedColumnName = "id")
        private Artist artistAlbums;

        public long getId() {
                return id;
        }

        public String getTitle() {
                return title;
        }

        public int getYear() {
                return year;
        }

        @JsonBackReference
        public Artist getArtistAlbums() {
                return artistAlbums;
        }
}
