package com.w1sh.watcher.services;

import com.w1sh.watcher.Indexation;
import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.dtos.QueryParamsDTO;

import java.util.List;

public interface MovieRequestService {

    List<MovieDTO> findAll(QueryParamsDTO queryParamsDTO);

    List<MovieDTO> findAll(Indexation indexation);
}
