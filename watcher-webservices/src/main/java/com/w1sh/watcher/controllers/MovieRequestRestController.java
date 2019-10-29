package com.w1sh.watcher.controllers;

import com.w1sh.watcher.dto.MovieDTO;
import com.w1sh.watcher.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/movies/search")
public class MovieRequestRestController {

    private final MovieService movieService;

    public MovieRequestRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping ("/{title}")
    public List<MovieDTO> findAll(@PathVariable String title){
        return movieService.findByTitle(title);
    }
}
