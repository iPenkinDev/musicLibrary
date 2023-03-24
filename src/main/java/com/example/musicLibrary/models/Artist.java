package com.example.musicLibrary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "artists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "artist_name")
    private String name;

    @Column(name = "artist_country")
    private String country;

    @Column(name = "artist_date_of_birth")
    private int dateOfBirth;

    @OneToMany(mappedBy = "artistAlbums", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Album> albums;

}
