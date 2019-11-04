package com.w1sh.watcher.services.impl;

import com.w1sh.watcher.clients.GenreClient;
import com.w1sh.watcher.dtos.GenreDTO;
import com.w1sh.watcher.services.GenreRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
@Service
public class GenreRequestServiceImpl implements GenreRequestService {

    private final GenreClient genreClient;

    @Override
    public List<GenreDTO> findAll() {
        log.info("Fetching all genres from TMDb API");
        return Stream.of(findAllMovieGenres(), findAllTvGenres())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<GenreDTO> findAllMovieGenres() {
        log.info("Fetching all movie genres from TMDb API");
        return genreClient.getAllMovieGenres();
    }

    @Override
    public List<GenreDTO> findAllTvGenres() {
        log.info("Fetching all TV genres from TMDb API");
        return genreClient.getAllTVGenres();
    }
}
