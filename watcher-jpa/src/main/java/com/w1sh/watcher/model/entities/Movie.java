package com.w1sh.watcher.model.entities;

import com.w1sh.watcher.model.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    private Integer id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "ADDED_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "RUNTIME")
    private Integer runtime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

}
