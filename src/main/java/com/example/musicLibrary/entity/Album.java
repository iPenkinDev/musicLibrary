package com.example.musicLibrary.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

        @JsonIgnore
        @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Song> songs;
}
