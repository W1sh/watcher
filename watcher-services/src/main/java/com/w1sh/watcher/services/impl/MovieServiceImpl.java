package com.w1sh.watcher.services.impl;

import com.w1sh.watcher.consumers.GenreConsumer;
import com.w1sh.watcher.consumers.MovieConsumer;
import com.w1sh.watcher.dto.MovieDTO;
import com.w1sh.watcher.entities.Genre;
import com.w1sh.watcher.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieConsumer movieConsumer;
    private final GenreConsumer genreConsumer;

    public MovieServiceImpl(MovieConsumer movieConsumer, GenreConsumer genreConsumer) {
        this.movieConsumer = movieConsumer;
        this.genreConsumer = genreConsumer;
    }

    @Override
    public MovieDTO findByTitle(String title) {
        List<Genre> genres = genreConsumer.getAllGenres();
        return movieConsumer.findByTitle(title).get(0);
    }
}
