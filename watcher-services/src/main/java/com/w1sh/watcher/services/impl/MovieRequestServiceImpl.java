package com.w1sh.watcher.services.impl;

import com.w1sh.watcher.clients.MovieConsumer;
import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.dtos.QueryParamsDTO;
import com.w1sh.watcher.services.MovieRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieRequestServiceImpl implements MovieRequestService {

    private final MovieConsumer movieConsumer;

    public MovieRequestServiceImpl(MovieConsumer movieConsumer) {
        this.movieConsumer = movieConsumer;
    }

    @Override
    public List<MovieDTO> findAll(QueryParamsDTO queryParamsDTO) {
        return movieConsumer.findByTitle(queryParamsDTO.getSearch());
    }
}
