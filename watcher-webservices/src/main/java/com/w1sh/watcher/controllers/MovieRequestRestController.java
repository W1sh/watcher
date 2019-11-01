package com.w1sh.watcher.controllers;

import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.dtos.QueryParamsDTO;
import com.w1sh.watcher.services.MovieRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/search/movies")
public class MovieRequestRestController {

    private static final Logger logger = LoggerFactory.getLogger(MovieRequestRestController.class);
    private final MovieRequestService movieRequestService;

    public MovieRequestRestController(MovieRequestService movieRequestService) {
        this.movieRequestService = movieRequestService;
    }

    @GetMapping
    public List<MovieDTO> findAll(@RequestParam Map<String, String> params){
        logger.info("Received request to search movies in TMDb API");
        QueryParamsDTO queryParamsDTO = new QueryParamsDTO(params);
        return movieRequestService.findAll(queryParamsDTO);
    }
}
