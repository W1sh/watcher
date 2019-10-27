package com.w1sh.watcher.responses;

import com.w1sh.watcher.entities.Genre;

import java.util.List;

public class GenreResponse {

    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
