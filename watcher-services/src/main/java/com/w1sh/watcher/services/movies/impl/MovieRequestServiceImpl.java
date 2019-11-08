package com.w1sh.watcher.services.movies.impl;

import com.w1sh.watcher.Indexation;
import com.w1sh.watcher.clients.MovieClient;
import com.w1sh.watcher.clients.common.RequestParameter;
import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.dtos.QueryParamsDTO;
import com.w1sh.watcher.services.movies.MovieRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MovieRequestServiceImpl implements MovieRequestService {

    private final MovieClient movieClient;

    @Override
    public MovieDTO find(Integer id) {
        RequestParameter parameter = new RequestParameter("append_to_response", "images,recommendations,similar");
        return movieClient.findById(id, parameter).orElseThrow();
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
