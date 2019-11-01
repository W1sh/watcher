package com.w1sh.watcher.services.impl;

import com.w1sh.watcher.clients.GenreConsumer;
import com.w1sh.watcher.dtos.GenreDTO;
import com.w1sh.watcher.services.GenreRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GenreRequestServiceImpl implements GenreRequestService {

    private static final Logger logger = LoggerFactory.getLogger(GenreRequestServiceImpl.class);
    private final GenreConsumer genreConsumer;

    public GenreRequestServiceImpl(GenreConsumer genreConsumer) {
        this.genreConsumer = genreConsumer;
    }

    @Override
    public List<GenreDTO> findAll() {
        logger.info("Fetching all genres from TMDb API");
        return Stream.of(findAllMovieGenres(), findAllTvGenres())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<GenreDTO> findAllMovieGenres() {
        logger.info("Fetching all movie genres from TMDb API");
        return genreConsumer.getAllMovieGenres();
    }

    @Override
    public List<GenreDTO> findAllTvGenres() {
        logger.info("Fetching all TV genres from TMDb API");
        return genreConsumer.getAllTVGenres();
    }
}
