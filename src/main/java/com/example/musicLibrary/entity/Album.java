package com.example.musicLibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "albums")
@Data
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

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "artist_id", referencedColumnName = "id")
        private Artist artist;

        @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Song> songs;
}
