package com.w1sh.watcher.services;

import com.w1sh.watcher.dtos.GenreDTO;

import java.util.List;

public interface GenreRequestService {

    List<GenreDTO> findAll();

    List<GenreDTO> findAllMovieGenres();

    List<GenreDTO> findAllTvGenres();
}
