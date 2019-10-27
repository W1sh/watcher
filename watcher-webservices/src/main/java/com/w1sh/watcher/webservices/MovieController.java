package com.w1sh.watcher.webservices;

import com.w1sh.watcher.dto.MovieDTO;
import com.w1sh.watcher.services.MovieService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

   private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @CrossOrigin
    @GetMapping
    public MovieDTO findAll(){
        return movieService.findByTitle("marvel");
    }
}
