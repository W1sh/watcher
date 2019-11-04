package com.w1sh.watcher.services.impl;

import com.w1sh.watcher.Indexation;
import com.w1sh.watcher.clients.MovieClient;
import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.dtos.QueryParamsDTO;
import com.w1sh.watcher.services.MovieRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieRequestServiceImpl implements MovieRequestService {

    private final MovieClient movieClient;

    public MovieRequestServiceImpl(MovieClient movieClient) {
        this.movieClient = movieClient;
    }

    @Override
    public List<MovieDTO> findAll(QueryParamsDTO queryParamsDTO) {
        return movieClient.findByTitle(queryParamsDTO.getSearch());
    }

    @Override
    public List<MovieDTO> findAll(Indexation indexation) {
        switch (indexation){
            case POPULAR: return movieClient.findPopular();
            case TOPRATED: return movieClient.findTopRated();
            case UPCOMING: return movieClient.findUpcoming();
            case NOWPLAYING: return movieClient.findNowPlaying();
            default: throw new IllegalArgumentException("Indexation not supported");
        }
    }
}
