package com.w1sh.watcher.responses;

import com.w1sh.watcher.dtos.GenreDTO;

import java.util.List;

public class GenreResponse {

    private List<GenreDTO> genres;

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }
}
