package com.w1sh.watcher.services;

import com.w1sh.watcher.dtos.GenreDTO;

import java.util.List;

public interface GenreService {

    List<GenreDTO> findAll();

    void saveAll(List<GenreDTO> genreDTOs);

    Boolean exists(GenreDTO genreDTO);
}
