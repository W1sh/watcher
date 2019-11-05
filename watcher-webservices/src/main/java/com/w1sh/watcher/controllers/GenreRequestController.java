package com.w1sh.watcher.controllers;

import com.w1sh.watcher.GenreType;
import com.w1sh.watcher.dtos.GenreDTO;
import com.w1sh.watcher.services.GenreRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping ("/search/genres")
@AllArgsConstructor
public class GenreRequestController {

    private final GenreRequestService genreRequestService;

    @GetMapping
    public List<GenreDTO> findAll(@RequestParam(required = false, defaultValue = "all") GenreType type){
        log.info("Received request to find all genres of type \"{}\"", type);
        switch (type) {
            case MOVIE: return genreRequestService.findAllMovieGenres();
            case TV: return genreRequestService.findAllTvGenres();
            case ALL: return genreRequestService.findAll();
            default: throw new IllegalArgumentException(
                    String.format("Type \"%s\" does not have a mapping assigned!", type));
        }
    }
}
