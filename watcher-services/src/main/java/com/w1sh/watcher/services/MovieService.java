package com.w1sh.watcher.services;

import com.w1sh.watcher.dtos.MovieDTO;

import java.util.List;

public interface MovieService {

    List<MovieDTO> findAll();

    List<MovieDTO> findByTitle(String title);

    MovieDTO findById(Integer id);
}
