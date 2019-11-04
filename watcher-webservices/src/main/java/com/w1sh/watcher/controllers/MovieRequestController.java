package com.w1sh.watcher.controllers;

import com.w1sh.watcher.Indexation;
import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.dtos.QueryParamsDTO;
import com.w1sh.watcher.services.MovieRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping ("/search/movies")
@AllArgsConstructor
public class MovieRequestController {

    private final MovieRequestService movieRequestService;

    @GetMapping("/{indexation}")
    public List<MovieDTO> findAll(@PathVariable String indexation){
        log.info("Received request to search movies in TMDb API with index type {}", indexation);
        Indexation index = Indexation.fromString(indexation);
        return movieRequestService.findAll(index);
    }

    @GetMapping
    public List<MovieDTO> findAll(@RequestParam Map<String, String> params){
        log.info("Received request to search movies in TMDb API");
        QueryParamsDTO queryParamsDTO = new QueryParamsDTO(params);
        return movieRequestService.findAll(queryParamsDTO);
    }
}
