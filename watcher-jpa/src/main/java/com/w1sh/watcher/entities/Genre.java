package com.w1sh.watcher.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table (name = "genres")
public class Genre {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Integer id;

    @Column(name = "VALUE", nullable = false, unique = true)
    private String value;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies;

}
