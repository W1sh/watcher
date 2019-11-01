package com.w1sh.watcher.controllers;

import com.w1sh.watcher.GenreType;
import com.w1sh.watcher.dtos.GenreDTO;
import com.w1sh.watcher.services.GenreRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/search/genres")
public class GenreRequestController {

    private static final Logger logger = LoggerFactory.getLogger(GenreRequestController.class);
    private final GenreRequestService genreRequestService;

    public GenreRequestController(GenreRequestService genreRequestService) {
        this.genreRequestService = genreRequestService;
    }

    @GetMapping
    public List<GenreDTO> findAll(@RequestParam(name = "type", required = false, defaultValue = "all") String type){
        logger.info("Received request to find all genres of type \"{}\"", type);
        GenreType genreType = GenreType.fromString(type);
        switch (genreType) {
            case MOVIE: return genreRequestService.findAllMovieGenres();
            case TV: return genreRequestService.findAllTvGenres();
            case ALL: return genreRequestService.findAll();
            default: throw new IllegalArgumentException(
                    String.format("Type \"%s\" does not have a mapping assigned!", type));
        }
    }
}
