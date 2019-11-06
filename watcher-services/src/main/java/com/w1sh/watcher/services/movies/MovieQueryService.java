package com.w1sh.watcher.services.movies;

import com.w1sh.watcher.dtos.MovieDTO;

import java.util.List;

public interface MovieQueryService {

    List<MovieDTO> findAll();

    List<MovieDTO> findByTitle(String title);

    MovieDTO findById(Integer id);
}
