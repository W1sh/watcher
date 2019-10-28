package com.w1sh.watcher.services;

import com.w1sh.watcher.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    List<MovieDTO> findByTitle(String title);
}
