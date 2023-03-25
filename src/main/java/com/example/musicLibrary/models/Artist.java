package com.example.musicLibrary.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "artists")
@Setter
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

    @OneToMany(mappedBy = "artistAlbums", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> albums;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonManagedReference
    public List<Album> getAlbums() {
        return albums;
    }
}
