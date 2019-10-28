package com.w1sh.watcher.webservices;

import com.w1sh.watcher.dto.MovieDTO;
import com.w1sh.watcher.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {

   private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{title}")
    public List<MovieDTO> findAll(@PathVariable String title){
        return movieService.findByTitle(title);
    }

    @GetMapping("/{title}/single")
    public MovieDTO findOne(@PathVariable String title){
        return movieService.findByTitle(title).get(0);
    }
}
