package com.w1sh.watcher.services.impl;

import com.w1sh.watcher.consumers.MovieConsumer;
import com.w1sh.watcher.dto.MovieDTO;
import com.w1sh.watcher.services.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieConsumer movieConsumer;

    public MovieServiceImpl(MovieConsumer movieConsumer) {
        this.movieConsumer = movieConsumer;
    }

    @Override
    public MovieDTO findByTitle(String title) {
        return movieConsumer.findByTitle(title);
    }
}
